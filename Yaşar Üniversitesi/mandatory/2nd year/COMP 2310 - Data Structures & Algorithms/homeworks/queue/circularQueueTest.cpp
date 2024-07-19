#include "circularQueue.h"
//21070006054 - ARDA HARMAN
//21070006054 - ARDA HARMAN
//21070006054 - ARDA HARMAN
//21070006054 - ARDA HARMAN
//21070006054 - ARDA HARMAN

#define prettyPrint(value) (value?"Yes":"No")

int main() {
    CircularQueue<int> queue = CircularQueue<int>(8);
    queue.enqueue(10);
    queue.enqueue(20);
    cout << "Is test case 1 correct ? : " << prettyPrint(queue.isEmpty() == false) << endl;
    cout << "Is test case 2 correct ? : "
         << prettyPrint((queue.getFrontIndex() == 0 && queue.getRearIndex() == 1) == true)
         << endl;
    queue.enqueue(30);
    queue.enqueue(40);
    queue.enqueue(50);
    queue.enqueue(60);
    queue.enqueue(70);
    queue.enqueue(80);
    cout << "Is test case 3 correct ? : " << prettyPrint(queue.isFull() == true) << endl;
    cout << "Is test case 4 correct ? : "
         << prettyPrint((queue.getFrontIndex() == 0 && queue.getRearIndex() == 7) == true)
         << endl;
    queue.enqueue(90);
    int value = queue.dequeue();
    cout << "Is test case 5 correct ? : " << prettyPrint(value == 10) << endl;
    cout << "Is test case 6 correct ? : "
         << prettyPrint((queue.getFrontIndex() == 1 && queue.getRearIndex() == 7) == true)
         << endl;
    value = queue.dequeue();
    value = queue.dequeue();
    value = queue.dequeue();
    cout << "Is test case 7 correct ? : " << prettyPrint(value == 40) << endl;
    cout << "Is test case 8 correct ? : "
         << prettyPrint((queue.getFrontIndex() == 4 && queue.getRearIndex() == 7) == true)
         << endl;
    cout << "Is test case 9 correct ? : " << prettyPrint(queue.searchElement(80) == true) << endl;
    queue.clear();
    return 0;
}

