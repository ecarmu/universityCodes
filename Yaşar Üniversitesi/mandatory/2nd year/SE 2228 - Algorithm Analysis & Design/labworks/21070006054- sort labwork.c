#include <stdio.h>

void mergeSort(int arr[], int lowerBound, int upperBound){
	
	if(lowerBound < upperBound){
	int mid = (lowerBound+upperBound)/2;
	
	mergeSort(arr, lowerBound, mid);
	mergeSort(arr, mid+1, upperBound);
	merge(arr, lowerBound, mid, upperBound);
	}
}

void merge(int arr[], int lowerBound, int mid, int upperBound){
	int arr_temp1[mid-lowerBound+1];
	int arr_temp2[upperBound - mid];
	
	int i;
	for(i = 0; i<mid-lowerBound+1; i++){
		arr_temp1[i] = arr[lowerBound + i];
	}
	
	for(i=0; i<upperBound - mid; i++){
		arr_temp2[i] = arr[mid + 1 + i];
	}
	
	int j = 0;
	i = 0;
	int k = lowerBound;
	while(i<mid-lowerBound+1 && j<upperBound - mid){
		if(arr_temp1[i]<=arr_temp2[j]){
			arr[k] = arr_temp1[i];
			i++;
		}
		else{
			arr[k] = arr_temp2[j];
			j++;
		}
		k++;
	}
	

	while(i < mid-lowerBound+1 ){
		arr[k] = arr_temp1[i];
		i++;
		k++;
	}
	
	while(j< upperBound - mid ){
		arr[k] = arr_temp2[j];
		j++;
		k++;
	}
	
}

void printArray(int arr[], int size){
	int i;
	for(i=0; i<size; i++){
		printf("%d ", arr[i]);
	}
}




void swap(int* a, int* b){
	
	int tmp;
	tmp = *a;
	*a = *b;
	*b = tmp;
}

void quickSort(int arr[], int low, int high){
	if(low<high){
		int pi = partition(arr, low, high);
	quickSort(arr, low, pi -1);
	quickSort(arr, pi + 1, high);
	}
}

int partition(int arr[], int low, int high){
	int pivot = arr[high];
	int i = low-1;
	
	int j;
	for(j=low; j<high;j++){
		if(arr[j]<pivot){
			i++;
			swap(&arr[i], &arr[j]);
		}
	}
	swap(&arr[i+1], &arr[high]);
	
	return i+1;
}

void main(){
	int arr[] = {34, 35,21,2, 3, 7, 9, 90, 357, 11, 145, 0, 100, 10}; // size is 14
	mergeSort(arr, 0,13);
	printf("This is merge sort:\n");
	printArray(arr, 14);
	
	int arr2[] = {34, 35,21,2, 3, 7, 9, 90, 357, 11, 145, 0, 100, 10}; // size is 14
	printf("\n\n");
	printf("This is quick sort:\n");
	quickSort(arr2, 0, 13);
	printArray(arr2, 14);
}

