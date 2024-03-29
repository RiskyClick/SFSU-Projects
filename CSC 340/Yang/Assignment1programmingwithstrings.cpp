/******************************************************************************

Online C++ Compiler.
Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

// Assignment1programmingwithstrings.cpp : Defines the entry point for the console application.
//

/******************************************************************************

Online C++ Compiler.
Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

// Assignment1programmingwithstrings.cpp : Defines the entry point for the console application.
//

//#include "stdafx.h"
#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

void printMenu() {
	cout << "MENU" << endl;
	cout << "c - Number of non - whitespace characters" << endl;
	cout << "w - Number of words" << endl;
	cout << "f - Find text" << endl;
	cout << "r - Replace all !'s" << endl;
	cout << "s - Shorten spaces" << endl;
	cout << "q - Quit" << endl << endl;
	cout << "Choose an option: " << endl;
}

int GetNumOfNonWSCharacters(string &mainString) {
	int chars = 0;
	for (unsigned int i = 0; i < mainString.size(); i++) {
		if (!isspace(mainString[i]))
			chars++;
	}
	return chars;
}

int GetNumOfWords(string &mainString) {
	int words = 1;
	for (unsigned int i = 0; i < mainString.size(); i++) {
		if (isspace(mainString[i])) {
			words++;
			while (isspace(mainString[i])) {
				i++;
			}
		}
	}
	return words;
}

int FindText(string mainString, string locate) {
	int count = 0;
	if (locate.size() == 0)
		return 0;
	for (size_t offset = mainString.find(locate);
		offset != string::npos;
		offset = mainString.find(locate, offset + locate.size()))
	{
		++count;
	}
	return count;
}

void ReplaceExclamation(string &mainString) {
	replace(mainString.begin(), mainString.end(), '!', '.');
	cout << mainString << endl;
}

void ShortenSpace(string &mainString) {
	int i = 0;
	int j = -1;
	int k = mainString.length();
	bool found = false;
	while (j++ < k && mainString[j] == ' ');
	while (j < k) {
		if (mainString[j] != ' ') {
			if ((mainString[j] == '.' || mainString[j] == ',' ||
				mainString[j] == '?') && i - 1 >= 0 &&
				mainString[i - 1] == ' ')
				mainString[i - 1] = mainString[j++];
			else
				mainString[i++] = mainString[j++];
			found = false;
		}
		else if (mainString[j++] == ' ') {
			if (!found) {
				mainString[i++] = ' ';
				found = true;
			}
		}
	}
	if (i <= 1)
		mainString.erase(mainString.begin() + i, mainString.end());
	else
		mainString.erase(mainString.begin() + i - 1, mainString.end());
}


int main(int argc, char * const argv[]) {

	cout << "Enter a sample text: " << endl;
	string mainString;
	string locate;
	string newString;
	char choice;
	getline(cin, mainString);
	cout << "You entered: " << endl << mainString << endl;

	printMenu();
	cin >> choice;
	while (choice != 'q') {
		switch (choice) {

		case 'c':
			cout << "Number of non-whitespace characters: " << GetNumOfNonWSCharacters(mainString) << endl;
			printMenu();
			cin >> choice;
			break;

		case 'w':
			cout << "Number of words: " << GetNumOfWords(mainString) << endl;
			printMenu();
			cin >> choice;
			break;

		case 'f':
			cin.ignore();
			cout << "\n\nEnter a word or phrase to be found:";
			getline(cin, locate);
			cout << "\n\n \"" << locate << "\" instances: " << FindText(mainString, locate) << " \n\n";
			printMenu();
			cin >> choice;
			break;

		case 'r':
			cout << "Edited text: ";
			ReplaceExclamation(mainString);
			cout << endl;
			printMenu();
			cin >> choice;
			break;

		case 's':
			cout << "Edited text: ";
			ShortenSpace(mainString);
			cout << mainString << endl;
			//	printMenu();
			cin >> choice;
			break;

		default:
			cout << "Invalid Choice" << endl;
			printMenu();
			cin >> choice;
			break;

		}
	}
	return 0;
}