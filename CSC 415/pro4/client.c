#include <stdio.h> 
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <unistd.h> 
#include <string.h> 
#define PORT 5001

int main(int argc, char const *argv[]){ 
	int sock = 0;
        int reader; 
	struct sockaddr_in serv_addr; 

	FILE *fp;
	long lSize;
	char *buff;

	//Open file for txt
	fp = fopen(argv[2], "rb");
	fseek(fp, 0L, SEEK_END);
	
	lSize = ftell(fp);
	rewind(fp);

	//throw the contentes of the file into buff
	buff = calloc(1, lSize + 1);
	fread(buff, lSize, 1, fp);
	fclose(fp);

	char buffer[5000] = {0}; 

	sock = socket(AF_INET, SOCK_STREAM, 0); 
	serv_addr.sin_family = AF_INET; 
	serv_addr.sin_port = htons(PORT); 
	 
	inet_pton(AF_INET, "127.0.0.1", &serv_addr.sin_addr);
	connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr));

	//send the contentes of the txt file
	send(sock , buff , strlen(buff) , 0 ); 
	
	//recive the counts and print out the values
	reader = read( sock , buffer, 5000); 
	printf("%s %s\n",argv[2], buffer ); 
	
	return 0; 
} 

