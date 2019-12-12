#include <unistd.h>
#include<sys/wait.h>
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#include <stdbool.h>
#define PORT 5001


int main(int argc, char const *argv[]){ 
   	int sfd;
   	int newSocket;
	pid_t pid;
 	bool infi = true;

    	int reader; 
    	struct sockaddr_in address; 
    	int opt = 1; 
   
    	int lenOfAddress = sizeof(address); 
    	char buffer[5000] = {0};  
       
	sfd = socket(AF_INET, SOCK_STREAM, 0);      
    	setsockopt(sfd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt)); 

    	address.sin_family = AF_INET; 
    	address.sin_addr.s_addr = INADDR_ANY; 
    	address.sin_port = htons( PORT ); 
       
    	bind(sfd, (struct sockaddr *)&address, sizeof(address)); 
    	listen(sfd, 3); 

	while(infi) {
	        newSocket = accept(sfd, (struct sockaddr *)&address, (socklen_t*)&lenOfAddress);
 		reader = read( newSocket , buffer, 5000);
		pid = fork();
		if(pid == 0){
   	
			char *fromClient = buffer;
    			int lines = 0;
    			int words = 0;
    			int chars = 0;

			while(*fromClient){
				if(*fromClient == '\n'){
					lines++;
					chars--;
				} 
				if(*fromClient == ' ') {
					chars--;
					words++;
				}
				chars++;
				fromClient++;
    			}

			printf("Chars: %d, Words: %d, Lines: %d\n", chars, words, lines);
			char toClient[1000];
			snprintf(toClient, sizeof(toClient), "Chars: %d, Words: %d, Lines: %d\n", chars, words, lines);
    			send(newSocket , toClient , strlen(toClient) , 0 );
		        exit(0);	
		}
	}
    return 0; 
} 
