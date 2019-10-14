//	LinkedBag340.cpp
//	Created by: CSC340 Student

#include <cstddef>
#include "Node.h"
#include "LinkedBag.h"
#include <string>
#include <stdio.h>      /* printf, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <cstdlib>

using namespace std;

template<typename ItemType>
bool LinkedBag<ItemType>::removeSecondNode340() {
    Node<ItemType>* secondNode = headPtr->getNext();
    bool possible = !isEmpty() && (secondNode != nullptr);

    if (possible) {
        secondNode->setItem(headPtr->getItem());
        Node<ItemType>* nodeToDeletePtr = headPtr;
        headPtr = headPtr->getNext();
        nodeToDeletePtr->setNext(nullptr);
        delete nodeToDeletePtr;
        nodeToDeletePtr = nullptr;
        --itemCount;
    }
    return possible;
}

template<typename ItemType>
bool LinkedBag<ItemType>::addEnd340(const ItemType& anEntry) {
    //needs to exits outside of function scope. hence new Node
    Node<ItemType>* entryNodePtr = new Node<ItemType>();
    entryNodePtr->setItem(anEntry);
    Node<ItemType>* current = headPtr;
    int count = 1;
    //find last node starting from front
    while ((current != nullptr) && (count < itemCount)) {
        current = current->getNext();
        ++count;
    }
    //next node is set
    current->setNext(entryNodePtr);
    ++itemCount;
    return true;
}

template<typename ItemType>
int LinkedBag<ItemType>::getCurrentSize340Iterative() const {
    Node<ItemType>* current = headPtr;
    int count = 0;
    //sim to add end function
    while (current != nullptr) {
        current = current->getNext();
        ++count;
    }
    return count;
}
//needed to utilize the curPtr parameter for my helper function
template<typename ItemType>
int LinkedBag<ItemType>::getCurrentSize340RecursiveHelper(Node<ItemType>* current) const {
    //if size is zero
    if (current == nullptr) {
        return 0;
    }
    else {
        current = current->getNext();
        //recursive call add + 1 before call
        return (1 + getCurrentSize340RecursiveHelper(current));
    }
}
// getCurrentSize340Recursive()
// Friendly reminder:
// - This function must be a recursive function of immediate recursion type.
// - Or (if needed) it can call one recursive function of immediate recursion type.
// - In either case, recursive call(s) must be used to accomplish the main task of the function.
template<typename ItemType>
int LinkedBag<ItemType>::getCurrentSize340Recursive() const {
    Node<ItemType>* current = headPtr;
    //function call for helper
    return getCurrentSize340RecursiveHelper(current);
}

template<typename ItemType>
int LinkedBag<ItemType>::getFrequencyOf340RecursiveHelper(Node<ItemType>* current, const ItemType& anEntry) const {
    //makeing sure the there is somthing there
    if (current == nullptr) {
        return 0;
    }
    //for when there is a match
    if (anEntry == current->getItem()) {
        current = current->getNext();
        //recursive helper call add + 1 first
        return (1 + getFrequencyOf340RecursiveHelper(current, anEntry));
    }
    //for when there is not a match but still need to travers
    else {
        current = current->getNext();
        return getFrequencyOf340RecursiveHelper(current, anEntry);
    }
}
// getFrequencyOf340Recursive()
// Friendly reminder:
// - This function must be a recursive function of immediate recursion type.
// - Or (if needed) it can call one recursive function of immediate recursion type.
// - In either case, recursive call(s) must be used to accomplish the main task of the function.
template<typename ItemType>
int LinkedBag<ItemType>::getFrequencyOf340Recursive(const ItemType& anEntry) const {
    Node<ItemType>* current = headPtr;
    return getFrequencyOf340RecursiveHelper(current, anEntry);
}

template<typename ItemType>
ItemType LinkedBag<ItemType>::removeRandom340() {
    string random = headPtr->getItem();
    Node<ItemType>* randPtr = getPointerTo(random);
    bool possible = !isEmpty() && (randPtr != nullptr);
    Node<ItemType>* current = headPtr;
    srand(time(NULL));
    // i must be > 1
    // random number grabber
    for (int i = 2; current != nullptr; ++i) {
        if (rand() % i == 0) {
            random = current->getItem();
        }
        current = current->getNext();
    }
    //same as the other remove funtuion that we went over in class for
    if (possible) {
        randPtr->setItem(headPtr->getItem());
        Node<ItemType>* deleter = headPtr;
        headPtr = headPtr->getNext();
        deleter->setNext(nullptr);
        delete deleter;
        deleter = nullptr;
        --itemCount;
    }
    return random;
}
// A recursive function of immediate recursion type is a function which calls itself.