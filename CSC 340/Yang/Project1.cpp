// ConsoleApplication1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include <string>;

using namespace std;


int main()
{
	string answer;
	cout << "Would you like to tell me your name? \n";
	getline(cin, answer);
	if (answer == "yes") {
		cout << "What's your name? \n";
		getline(cin, answer);
		cout << "Hello " << answer << ".\n";
	}
	else {
		cout << "Alright we will call you: Anon";
	}
	system("pause>nul");
    return 0;
}

