#include <iostream>
//21070006054 - ARDA HARMAN
//21070006054 - ARDA HARMAN
//21070006054 - ARDA HARMAN
//21070006054 - ARDA HARMAN
//21070006054 - ARDA HARMAN

using namespace std;

template<class T>
class CircularQueue {
private:
    int front;
    int rear;
    int size;
    T *elements;
public:

    CircularQueue(int size) {
        front = rear = -1;
        elements = new T[size];
        this->size = size;
    }

    int getFrontIndex() {
        return front;
    }

    int getRearIndex() {
        return rear;
    }

    // Fill the function and add necessary control statements to prevent program crash
    void enqueue(T element) {
    	if(isFull()){
    		cout<<"Queue is full"<<endl;
		}
		
		else{
			
		if(front==-1 && rear==-1){
			front++;
			rear++;
			elements[rear] = element;
		}
			
		else if(rear == size -1){
			rear = 0;
			elements[rear] = element;
		}
		else{
			rear++;
			elements[rear] = element;
		}
	}
    
}

    // Fill the function and add necessary control statements to prevent program crash
    T dequeue() {
		T valuePopped = elements[front];
		front++;
		return valuePopped;
    }

    // Check that if the given element is existing.
    // Fill the function and add necessary control statements to prevent program crash
    bool searchElement(T element) {
		int i;
		
		for(i=0; i<size; i++){
			if(elements[i] == element){
				return true;
			}
		}
		return false;
    }

    void clear(){
        delete[] elements;
    }

    void print() {
        cout << "CircularQueue" << endl;
        if (front != -1) {
            if (front <= rear) {
                for (int i = front; i <= rear; i++) {
                    cout << elements[i] << "->";
                }
                cout << endl;
            } else {
                for (int i = front; i <= size - 1; i++) {
                    cout << elements[i] << "->";
                }
                for (int i = 0; i <= rear; i++) {
                    cout << elements[i] << "->";
                }
                cout << endl;
            }
        }
    }

    bool isEmpty() {
        return front == -1;
    }

    bool isFull() {
        return ((front == 0) && (rear == size - 1)) || (front == rear + 1);
    }
};

