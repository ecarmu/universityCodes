#include <random>
#include <iostream>

using namespace std;


//// 21070006054 - ARDA HARMAN


int *generateRandomArray(int size) {
    random_device dev;
    mt19937 rng(dev());
    uniform_int_distribution<mt19937::result_type> randInt(1, size - 1); // distribution in range [1, 100]
    int *array = new int[size];
    for (int i = 0; i < size; i++) {
        array[i] = randInt(rng);
    }
    return array;
}

void printArray(int *array, int size) {
    for (int i = 0; i < size; i++) {
        cout << array[i] << ",";
    }
    cout << endl;
}

// https://www.cs.usfca.edu/~galles/visualization/CountingSort.html
int *countingSort(int *array, int size) {
    int *sortedArray = new int[size];
    
    int *tmpArr = new int[size+1];
    
    int i= 0;
    
    for(i=0; i<size; i++)
    	tmpArr[i] = 0;
    	
    for(i=0; i<size; i++){
    	tmpArr[ array[i] ] += 1;
	}
    
    for(i=0; i<size; i++){
    	tmpArr[i+1] += tmpArr[i];
	}
    
	i=size-1;
	for(; i>=0; i--){
		tmpArr[ array[i] ] -= 1;
		sortedArray [ tmpArr[ array[i] ] ] = array[i] ;
	}

    return sortedArray;
}



int *bubbleSort(int *array, int size) {
    int *sortedArray = new int[size];
    
    int i,j;
    
    for(i=0; i<size; i++){
    	sortedArray[i] = array[i];
	}
    
    for(i=0; i<size; i++){
    	for(j=0; j<size-1; j++){
    		
    		if(sortedArray[j+1] < sortedArray[j]){
    			int tmp = sortedArray[j+1];
    			sortedArray[j+1] = sortedArray[j];
    			sortedArray[j] = tmp;
			}
		}
	}


    return sortedArray;
}

int *selectionSort(int *array, int size) {
    int *sortedArray = new int[size];

	int i,j;
    
    for(i=0; i<size; i++){
    	sortedArray[i] = array[i];
	}
	
	
    
    for(i=0; i<size; i++){
    	int min = sortedArray[i];
    	int indexOfMin = i;
    	
		for(j=i; j<size; j++){
    		
    		if(sortedArray[j] < min){
    			min = sortedArray[j];
    			indexOfMin = j;
			}
			
					
			}
			
			int tmp = sortedArray[i];
    		sortedArray[i] = sortedArray[indexOfMin];
    		sortedArray[indexOfMin] = tmp;
		}
	
	
    return sortedArray;
}

int main() {
    int size = 10;
    int *array = generateRandomArray(size);
    cout << "Before Sort " << endl;
    printArray(array, size);
    cout << "After Counting Sort" << endl;
    printArray(countingSort(array, size), size);
    cout << "After Bubble Sort" << endl;
    printArray(bubbleSort(array, size), size);
    cout << "After Selection Sort" << endl;
    printArray(selectionSort(array, size), size);
    return 0;
}
