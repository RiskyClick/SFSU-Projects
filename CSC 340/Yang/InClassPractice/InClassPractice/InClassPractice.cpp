// InClassPractice.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include<iostream>
#include<string>

using namespace std;

void print(string s1, string s2, string s3);

int main(int argc, const char * argv[])
{
	string s1("Hello "), s2("good "), s3("bye! ");

	print(s1, s2, s3);
	print(s1, s1, s1 = "bye! ");
	system("pause");
    return 0;
}

void print(string s1, string s2, string s3) {
	cout << s1 << s2 << s3 << endl;
}
