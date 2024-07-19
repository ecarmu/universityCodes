#include <iostream>




/********
 21070006054 - ARDA HARMAN
 21070006054 - ARDA HARMAN
  21070006054 - ARDA HARMAN
 21070006054 - ARDA HARMAN
  21070006054 - ARDA HARMAN
 21070006054 - ARDA HARMAN
  21070006054 - ARDA HARMAN
 21070006054 - ARDA HARMAN
**********/










using namespace std;

template<class T>
class LinkListNode {
public:
    T data;
    LinkListNode *next;

    LinkListNode(T data) {
        this->data = data;
        next = NULL;
    }
};

template<class T>
class LinkList {
    LinkListNode<T> *head;
public:
    LinkList() {
        head = NULL;
    }

    bool isEmpty() {
        return head == NULL;
    }

    // This function is implemented for testing.Don't use this function.
    LinkListNode<T> *getHead() {
        return head;
    }

    void insertFirst(LinkListNode<T> *node) {
		LinkListNode<T> *tmp = head;
		
		head = node;
		
		node->next = tmp;
    }

    // Insert "after" the node that contains searchData value
    void insert(LinkListNode<T> *node, T searchData) {
		
		LinkListNode<T> *tmp = head;
		
		while(tmp->data != searchData){
			tmp = tmp->next;
		}
		node->next = tmp->next;
		tmp->next = node;
    }


    void insertLast(LinkListNode<T> *node) {
    	LinkListNode<T> *tmp = head;
    	
    	while(tmp->next != NULL){
			tmp = tmp->next;
		}
		
		tmp->next = node;
    }
    
    

    LinkListNode<T> *removeElement(T searchData) {
		LinkListNode<T> *tmp = head;
    	
    	while(tmp->data != searchData){
			tmp = tmp->next;
		}
		return tmp;
    }

    //Element on the tail, returned but not removed from the linklist
    LinkListNode<T> *topElement() {
        LinkListNode<T> *tempHead = head;
        LinkListNode<T> *topElement = NULL;
        while (tempHead != NULL) {
            if (tempHead->next == NULL) {
                topElement = tempHead;
                break;
            }
            tempHead = tempHead->next;
        }
        return topElement;
    }
    

    void print() {
        //You have to use temp node when you move the head temporarily,
        // otherwise the node position won't be correct
        cout << "Stack Info" << endl;
        LinkListNode<T> *tempHead = head;
        while (tempHead != NULL) {
            cout << tempHead->data << "->";
            tempHead = tempHead->next;
        }
        cout << endl;
    }

    void clear() {
        LinkListNode<T> *tempHead = head;
        LinkListNode<T> *next = NULL;
        while (tempHead != NULL) {
            next = tempHead->next;
            delete tempHead;
            tempHead = next;
        }
    }
};
