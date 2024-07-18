#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Do not forget to do big oh analyses for each function.

void max_selection_sort(int *arr, int n);
void max_heap_sort(int *arr, int n);
int* build_max_heap(int *arr, int n);
void max_heapify(int *arr, int n, int i);
int left(int index);
int right(int index);
void swap(int *a, int *b);
void max_selection_sort_accuracy_test();
void max_heap_sort_accuracy_test();
void comparison_test();


void max_selection_sort(int *arr, int n) { 
	
	int i; 
	int* arr2 = arr;
	int size = 10;
	int min_idx;
		int j;
	for (i = 0; i < size - 1; i++) { //O(n)
     min_idx = i;
    for (j = i + 1; j < size; j++) { //O(n)

      if (arr2[j] > arr2[min_idx])
        min_idx = j;
    
	}	
    
    swap(&arr2[min_idx], &arr2[i]);
		
}
//Total is O(n^2)
  
  printf("New version of array: ");
  for(i=0; i<size; i++)
	printf("%d ", arr2[i]);
	
	printf("\n\n");
}

void max_heap_sort(int *arr, int n) { //O(nlogn)
	int heap_size = n;
	
	int* Arr = (int*)malloc(sizeof(int)*n);
	Arr = build_max_heap(arr,n); //O(nlogn)
	
	int i;
	for(i = n-1; i>=2 ; i-- )	{ //0(n)
		
	swap(&Arr[ 1 ], &Arr[ i ]);
	heap_size = heap_size-1;
	max_heapify(Arr, 1, heap_size); //O(logn)
	}
}

int *build_max_heap(int *arr, int n) {
	
	int* arrClone = (int*)malloc(sizeof(int) * n );
	
	int a;
	for(a=0; a<n; a++)
		arrClone[a] = arr[a];
	
	int i = n/2; 
	for(; i>=0; i--)
		max_heapify(arrClone, n, i);
	

	
	return arrClone;
}

void max_heapify(int *arr, int n, int i) {
	// TODO: Fill this function.
	int indexOf_LEFT_child = left(i);
	int indexOf_RIGHT_child = right(i);
	
	int max = i;
	
	if(indexOf_LEFT_child<n && arr[indexOf_LEFT_child] > arr[i]){
		max = indexOf_LEFT_child;
	}
	else{
		max = i;
	}
	
	if(indexOf_RIGHT_child < n && arr[indexOf_RIGHT_child] > arr[max])
	max = indexOf_RIGHT_child;
	
	if(max!= i)
	{
	swap(&arr[i], &arr[max]);
	
	max_heapify(arr, n, max);
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

void max_selection_sort_accuracy_test() {
	printf("STARTING MAX SELECTION SORT ACCURACY TEST\n");
	int arr[] = {3, 7, 4 ,1, 8, 12, 13, 5, 9, 11}; 
	int size = 10;

	max_selection_sort(arr, size);
	
	printf("ENDING MAX SELECTION SORT ACCURACY TEST\n");
}

void max_heap_sort_accuracy_test() {
	printf("STARTING MAX HEAP SORT ACCURACY TEST\n");
		int arr[] = {3, 7, 4 ,1, 8, 12, 13, 5, 9, 11}; 
	int size = 10;
	
int i;
	
	
	int* heaped_arr = build_max_heap(arr, size);
	

	printf("New version of array: ");
	for(i=0; i<size; i++)
	printf(" %d ", heaped_arr[i]);
	
	printf("\n\n");
	
	
	printf("ENDING MAX HEAP SORT ACCURACY TEST\n\n");
}

void comparison_test() {
	printf("STARTING COMPARISON TEST\n");
	
	clock_t starting1, starting2, ending1, ending2;
	double time;
	
	starting1 = clock();
	
	max_selection_sort_accuracy_test();
	
	printf("Time of selection sort : %f ", (double) ending1-starting1);
	
	
	starting2 = clock();
	max_heap_sort_accuracy_test();
	
	printf("Time of heap sort : %f ", (double) ending2-starting2);
	
	// TODO: Do your comparisons here.
	printf("ENDING COMPARISON TEST\n\n");
	// Write your conclusion here:
	//Since, complexity of heap sort is less than selection sort; heap sorted just a little bit quickly. But, since my pc was fast, this difference was almost impossible to recognize
}

