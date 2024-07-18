#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int *build_min_heap(int *arr, int n);
void min_heapify(int *arr, int n, int i);
int left(int index);
int right(int index);
void swap(int *a, int *b);
void test();


int *build_min_heap(int *arr, int n) {
	
	int* arrClone = (int*)malloc(sizeof(int) * n );
	
	int a;
	for(a=0; a<n; a++)
		arrClone[a] = arr[a];
	
	int i = n/2; 
	for(; i>=0; i--)
		min_heapify(arrClone, n, i);
	

	
	return arrClone;
}

void min_heapify(int *arr, int n, int i) {
	// TODO: Fill this function.
	int indexOf_LEFT_child = left(i);
	int indexOf_RIGHT_child = right(i);
	
	int min;
	
	if(indexOf_LEFT_child<n && arr[indexOf_LEFT_child] < arr[i]){
		min = indexOf_LEFT_child;
	}
	else{
		min = i;
	}
	
	if(indexOf_RIGHT_child < n && arr[indexOf_RIGHT_child] < arr[min])
	min = indexOf_RIGHT_child;
	
	if(min!= i)
	{
	swap(&arr[i], &arr[min]);
	
	min_heapify(arr, n, min);
	}
	
}

int left(int index) {
	
	return 2*index + 1;
}

int right(int index) {
	
	return 2*index + 2;
}

void swap(int *a, int *b) {
	int tmp = *a;
	*a = *b;
	*b = tmp;
}

void test() {
	int arr[] = {3, 7, 4 ,1, 8, 12, 13, 5, 9, 11}; 
	int size = 10;
	
	printf("1st version of array: ");
	int i = 0;
	
	for(; i<size; i++)
	printf(" %d ", arr[i]);
	
	printf("\n\n");
	
	
	
	int* heaped_arr = build_min_heap(arr, size);
	
	

	printf("New version of array: ");
	for(i=0; i<size; i++)
	printf(" %d ", heaped_arr[i]);
	
}

