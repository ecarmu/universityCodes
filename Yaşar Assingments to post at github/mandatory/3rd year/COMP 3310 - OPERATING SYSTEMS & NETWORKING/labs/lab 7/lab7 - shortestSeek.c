#include<stdio.h>
#include <stdbool.h>


void shortestSeek(int size){
	
	int i;
	int arr[size];
	int arr2 [size];
	int totalOperaitons = 0;
	
	printf("Enter initial head position: \n");
	scanf("%d", &arr[0]);
	
	printf("Enter sequence: \n");
	for(i = 1; i < size; i++){
		scanf("%d", &arr[i]) ;
	}
	
	int indexBefore = 0;
	int indexAfter;
	arr2[0] = arr[indexBefore];
	
	for(i = 1; i < size; i++){
		indexAfter = minFarkVericiIndex(arr, size, indexBefore);
		int dif = arr[indexAfter] -arr[indexBefore];
		if(dif < 0)
		dif *= -1;
		
		totalOperaitons += dif;
		arr2[i] = arr[indexAfter];
		arr[indexBefore]  = -1;
		indexBefore = indexAfter;
	}
	
	printf("Seek sequence is: \n");
	
	for(i = 0; i < size; i++){
		printf("%d\n", arr2[i]);
	}
	
	printf("Total number of seek operaitons: %d", totalOperaitons);
	
	
}

int minFarkVericiIndex(int arr[], int size, int indexOfnumComp){
	
	int i, minIndex = 0;
	int minDif = 999999999;
	
	
	for(i = 0; i < size; i++){
		
		if(i == indexOfnumComp || arr[i] == -1)
		continue;
		
		int currentDiff = arr[i] - arr[indexOfnumComp];
		if(currentDiff < 0)
			currentDiff *= -1;

		
		if(currentDiff < minDif){
			minDif = currentDiff;
			minIndex = i;
		}
	}
	
	
	
	
	return minIndex;
}


void main(){
	shortestSeek(9);
}
