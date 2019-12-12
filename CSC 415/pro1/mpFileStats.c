/***********************

  File: fileStats.c
  Compile: gcc fileStats.c -o fileStats
  Run: ./fileStats [file1] [file2] ...

  Description: Similar to Unix utility wc
               [file1], [file2] etc are text files. fileStats counts the
               number of lines, words and characters in each file, reports
               the counts for each file, and total counts over all the files.

***********************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAXCHARS 80

struct FileInfo {
  char *name;    /* name of file */
  int numLines;  /* number of lines in file */
  int numWords;  /* number of words in file */
  int numChars;  /* number of characters in file */
} fileInfo;

int countWords(char *);

int main(int argc, char **argv) {
  FILE *fp;
  struct FileInfo *info; /* array of counts for each file */
  int numLines = 0, numWords = 0, numChars = 0; /* total counts */
  char inString[MAXCHARS];
  char *rs;
//HERE ARE THE NEW DATA TYPES I AM USING
//I DECLARED 500 SPACE FOR THEM JUST FOR READABLITY
  pid_t id;
  int pipes[2];
  char inbuf[500];
  char str[500];
  int rc = pipe(pipes);
  char temp[500];

  /* allocate array of structs containing counts for each file */
  info = (struct FileInfo *) malloc((argc-1) * sizeof(struct FileInfo));

  for (int i=0; i<argc-1; i++) {
    /* open an input file, initialize struct of counts */
    fp = fopen(argv[i+1], "r");
    if (fp == NULL) {
      printf("Error: cannot open file\n");
      return 0;
    }

    info[i].name = (char *) malloc(MAXCHARS * sizeof(char));
    strncpy(info[i].name, argv[i+1], MAXCHARS);
    info[i].numLines = 0;
    info[i].numWords = 0;
    info[i].numChars = 0;

    /* read each line, update counts */
    rs = fgets(inString, MAXCHARS, fp);
    
    while (rs != NULL) {
      info[i].numLines++;
      info[i].numChars += strlen(inString);
      info[i].numWords += countWords(inString);
      rs = fgets(inString, MAXCHARS, fp);
    }
//THE BEGING OF MY FORK. THE CHILD HANDLES THE PRINTING OF EACH TXT FILE
//IT GETS ITS DATA FROM A PIPE PASSED THOUGH THE PERENT
    id = fork();
    if(id == 0) {
	rc = read(pipes[0], &inbuf, sizeof(inbuf));
	//IN ORDER TO GET ACTUATE ID I ADDED THAT PART HERE AND CONCATONATED IT WITH THE MESSAGE
	//PASSED FROM THE PARANT
	printf("Process Id: %d Name: %s\n", getpid(), inbuf);
	return 0;

    } else {
	    //BLANKS OUT THE STRING
	strcpy(str, "");
	//CONCATS BLACK STRING WITH INFO FROM THE STRUCT
	strcat(str, info[i].name);
        strcat(str, " Lines: ");
	//ITOA() NEVER WORKED FOR ME SO I USED SPRINTF TO CONVERT
	//INTS TO STRING AND THEN CONCATOLATED
	sprintf(temp, "%d", info[i].numLines);
	strcat(str, temp);
	        
	strcat(str, " Words: ");
        sprintf(temp, "%d", info[i].numWords);
	
	strcat(str, temp);
        strcat(str, " Characters: ");
        
	sprintf(temp, "%d", info[i].numChars);
	strcat(str, temp);
		
	rc = write(pipes[1], &str, sizeof(str));
	wait(NULL);
    }
  }

  for (int i=0; i<argc-1; i++) {
    numLines += info[i].numLines;
    numWords += info[i].numWords;
    numChars += info[i].numChars;
  }

  printf("Total: %d lines, %d words, %d characters\n",
	 numLines, numWords, numChars);

}

/***********************

  int countWords(char *inS)

  inS: input null-terminated string
  
  returns number of words in string, delimited by spaces

***********************/

int countWords(char *inS) {
  char *token;
  int numTokens = 0;
  int i=0;

  for (i=1; i<strlen(inS); i++) {
    if ((isalnum(inS[i-1]) || ispunct(inS[i-1])) && (inS[i] == ' ')) {
      numTokens++;
    }
  }
      
  if (isalnum(inS[strlen(inS)-2]) || ispunct(inS[strlen(inS)-2])) {
    numTokens++;
  }
  return numTokens;
}
