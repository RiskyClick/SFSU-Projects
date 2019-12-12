#include <pthread.h>
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

char inString[MAXCHARS];
char *rs;
FILE *fp;

void *runThread(void *);

int countWords(char *);

int main(int argc, char **argv) {
  	struct FileInfo *info; /* array of counts for each file */
  	int numLines = 0, numWords = 0, numChars = 0, pos = 0; /* total counts */

	pthread_t *thread;
	thread = (pthread_t *) malloc(argc * sizeof(pthread_t));
	int iret, i;

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

		pthread_create(&thread[i], NULL, runThread, (void *) info[i].name);
    /* read each line, update counts */
    		rs = fgets(inString, MAXCHARS, fp);
    		while (rs != NULL) {
                	info[i].numLines++;
                	info[i].numChars += strlen(inString);
                	info[i].numWords += countWords(inString);
                	rs = fgets(inString, MAXCHARS, fp);
        	}

  	}

	for (int i = 0; i < argc -1; i++) {
    		if (((iret = pthread_join( thread[i], NULL)))) {
      			printf("Error terminating thread %d\n", i);
      			exit(0);
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

void *runThread( void *nameFrom ) {
	char * name;
	name = (char*) nameFrom;

	fp = fopen(nameFrom, "r");

	if (fp == NULL) {
   	     printf("Error: cannot open file\n");
             return 0;
        }


	rs = fgets(inString, MAXCHARS, fp);
	struct FileInfo threader;
	
	threader.numLines = 0;
	threader.numChars = 0;
	threader.numWords = 0;

        while (rs != NULL) {
        	threader.numLines++;
                threader.numChars += strlen(inString);
                threader.numWords += countWords(inString);
                rs = fgets(inString, MAXCHARS, fp);
        }
        printf("%s: %d lines, %d words, %d characters, Thread ID:%ld\n", name,
        threader.numLines, threader.numWords, threader.numChars, pthread_self());

}

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

