#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <sys/types.h>
#include <pthread.h>
#include <semaphore.h>

void *childFunc(void *ptr);

#define MAXCHARS 80

struct FileInfo {
  	char *name;    /* name of file */
  	int numLines;  /* number of lines in file */
  	int numWords;  /* number of words in file */
  	int numChars;  /* number of characters in file */
} fileInfo;

struct FileInfo *info; /* array of counts for each file */
int countWords(char *);
int filesRead = 0;
int totals[3];
sem_t sem;


int main(int argc, char **argv) {
  	int numLines = 0, numWords = 0, numChars = 0; /* total counts */
  	char inString[MAXCHARS];

  	pthread_t *child;
  	int  iret1;
  	int *tid;
	filesRead = argc - 1;
	sem_init(&sem, 0, 0);
 
  	/* allocate array of structs containing counts for each file */
  	info = (struct FileInfo *) malloc((argc-1) * sizeof(struct FileInfo));

  	for (int i=0; i<argc-1; i++) {
    		info[i].name = (char *) malloc(20 * sizeof(char));
    		strncpy(info[i].name, argv[i+1], 20);    
  	}

  	child = (pthread_t *) malloc((argc-1) * sizeof(pthread_t));

  	tid = (int *) malloc((argc-1) * sizeof(int));
  	for (int i=0; i<argc-1; i++) {
    		tid[i] = i;
  	}

  	for (int i=0; i<argc-1; i++) {
    		/* spawn a thread */
    		if ((iret1 = pthread_create(&child[i], NULL, childFunc, &tid[i]))) {
      			printf("Error creating thread\n");
      			exit(0);
    		}
  	}

	sem_wait(&sem);

  	printf("Total Files: %d, Total Lines: %d, Total Words: %d, Total Chars: %d\n", argc-1, totals[0], totals[2], totals[1]);

  	free(info);
  	free(tid);
  	free(child);
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

void *childFunc(void *ptr) {
  	FILE *fp;
  	char *rs;
  	char inString[MAXCHARS];
  	int myIndex = *((int *) ptr);
  	/* open an input file, initialize struct of counts */
  	fp = fopen(info[myIndex].name, "r");
  	if (fp == NULL) {
    		printf("Error: cannot open file\n");
    		return 0;
  	}
  	info[myIndex].numLines = 0;
  	info[myIndex].numWords = 0;
  	info[myIndex].numChars = 0;

  	/* read each line, update counts */
  	rs = fgets(inString, MAXCHARS, fp);
  
  	while (rs != NULL) {
    		info[myIndex].numLines++;
    		info[myIndex].numChars += strlen(inString);
    		info[myIndex].numWords += countWords(inString);
    		rs = fgets(inString, MAXCHARS, fp);
  	}

	totals[0] += info[myIndex].numLines;
	totals[1] += info[myIndex].numChars;
	totals[2] += info[myIndex].numWords;
	
  	printf("Thread %ld %s: %d lines, %d words, %d characters\n",
	 	(long) pthread_self(), info[myIndex].name,
	 	info[myIndex].numLines, info[myIndex].numWords, info[myIndex].numChars);
        filesRead--;
	if(filesRead <= 0){
                sem_post(&sem);
        }

  	pthread_exit(0);
}
