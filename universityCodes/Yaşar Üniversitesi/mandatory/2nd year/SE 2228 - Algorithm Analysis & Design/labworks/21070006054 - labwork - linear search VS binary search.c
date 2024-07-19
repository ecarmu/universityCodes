#include <stdio.h>
#include <stdlib.h>

int linearSearch(int arr[], int size, int data){
	
	int i; // 1   ->   we declared a variable, this operation is done 1 times
	for(i = 0; i<size; i++){ // (size yerine n diyelim) n + 1   ->   this loop works n+1 times. Because controlling i<size operation is done size+1 times. Until i becomes size from 0, we do that opeartion
		if(arr[i] == data){ // n  -> if block and the operation inside is done 1 times each time until loop ends. Totally n*1 times, they were operated
			return i+1; // n
		}
	}
	
	return -1; 
}
// Complexity of linearSearch is O(n) = 3n + 1


int binarySearch(int arr[], int size, int data){
	
	int i; // 1 ->   we declared a variable, this declaration operation is done 1 times 
	int counter = 0; // 1
	int start_point = 0; // 1
	int end_point = size-1; // 1
	int mid_point; // 1
	
	while(start_point <= end_point){ //log(n)   -> this while loop works until start_point <= end_point condition isn't satisfied anymore. 
	// Because each time we divide by 2. This means log will be on base 2. 
	
	// each operaiton under here will be done 1 times per times until loop is over. Loop will be over afer log(n) times
		mid_point = (end_point + start_point) / 2; // log(n)
		
		if(arr[mid_point] == data){ // log(n)
			counter++; // log(n)
			return counter; // log(n)
		}
		else if(arr[mid_point] < data){ // log(n)
			counter++; // log(n)
			start_point = mid_point + 1; // log(n)
		}
		else if(arr[mid_point] > data){ // log(n)
			counter++; // log(n)
			end_point = mid_point - 1; // log(n)
		}
	}
	
	return -1;
		
}
// Complexity of binarySearch is O(log(n)) = 3*log(n) + 5



void main(){
	
	int arr[10000];
	int i;
	for(i = 0; i<10000; i++){
		arr[i] = i;
	}	
	
	
	//for proving the efficeny of binary search over linear search, this code lþnes will be used
	int iterationAmountOfLineerSearch = linearSearch(arr, 10000, 3900); 
	int iterationAmountOfBinarySearch = binarySearch(arr, 10000, 3900); 
	
	printf("Iteration Amount of lineer search: %d\n Iteration Amount of Binary search: %d\n", iterationAmountOfLineerSearch, iterationAmountOfBinarySearch);


}
