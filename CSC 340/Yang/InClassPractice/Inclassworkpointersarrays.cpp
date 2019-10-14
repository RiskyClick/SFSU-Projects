/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>

using namespace std;

struct wordFreq 
{
    string word;
    int freq;
};

const int MAX_CAPACITY = 100;

int main(int argc, const char * srgv[])
{
    //declare an int pointer p_int, and int i1, and double d1
    int *p_int = nullptr, i1 = 0;
    double d1 = 0.0;
    
    //declare a wordFreq pointer p_WF and a wrodFreq object wf1: hello" /10
    wordFreq *p_WF = nullptr;
    wordFreq wf1 = {"hello", 10};
    
    p_WF = &wf1;
    
    //Print out wf1 vaules using p_WF
    cout << (*p_WF).word << (*p_WF).freq << endl;
    //This is the same thing with difrent syntas
    cout << p_WF -> word << p_WF -> freq << endl;
    
    //declare a fixed size array of 100 integers: arrayInt
    int arrayInt [MAX_CAPACITY];
    int array_size = 0;
    
    //Make &arrayInt[20] the pointee of p_int withoout using &
    p_int += 20;
        
    //make arrayInt the pointee of p_int
    p_int = arrayInt;
    //This is also the same thing
    p_int = &arrayInt[0];
    
    //nake i1 i1 a pointee if p_int
    p_int = &i1;
    
    //change i1 valuie to 100 using p_int
    *p_int = 100;
    return 0;
}



