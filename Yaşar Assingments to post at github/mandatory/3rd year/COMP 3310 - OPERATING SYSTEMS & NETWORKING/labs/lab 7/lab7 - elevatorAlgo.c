#include <stdio.h>
#include <stdbool.h>

void selectionSort(int arr[], int n);
int findHead(int arr[], int size, int head);
void elevatorAlgo(int size);

void swap(int* xp, int* yp) 
{ 
    int temp = *xp; 
    *xp = *yp; 
    *yp = temp; 
} 

int findHead(int arr[], int size, int head) {
    int f;

    for (f = 0; f < size; f++) {
        if (head == arr[f])
            return f;
    }

    return -1;    
}


void elevatorAlgo(int size){
	
	int i, j;
	int arr[size];
	int totalOperaitons = 0;
	char direction[5];
	int head;
	int directionNumber;
	
	printf("Enter initial head position: \n");
	scanf("%d", &arr[0]);
	head = arr[0];
	
	printf("Enter direction: \n");
	scanf("%s", direction);
	
	if(strcmp(direction,"right"))
		directionNumber = -1;
	else
		directionNumber = 1;
	
	
	printf("Enter sequence: \n");
	for(i = 1; i < size; i++){
		scanf("%d", &arr[i]) ;
	}

	selectionSort(arr, size);
	int indexOfHead = findHead(arr, size, head);
	
	printf("Seek sequence is: \n");
	for(i = indexOfHead, j = 0; j < size + 1; i = (i + directionNumber) % size, j++){
		if(i < 0 && strcmp(direction,"right")){
			directionNumber = 1;
			i += indexOfHead + + directionNumber;
		
			totalOperaitons += arr[i - directionNumber];
			
			continue;
		}
		else if(i > size && strcmp(direction,"left")){
			directionNumber = -1;
			i -= indexOfHead + directionNumber;
			
			totalOperaitons += arr[i - directionNumber];
			continue;
		}
		
		int dif = arr[i] - arr[i - directionNumber];
		if(dif < 0)
			dif *= -1;
		
		totalOperaitons += dif;
		
		printf("%d indexx: %d\n", arr[i], i);
	}
	
	
	
	printf("Total number of seek operaitons: %d", totalOperaitons);
	
	
}


// Function to perform Selection Sort 
void selectionSort(int arr[], int n) 
{ 
    int i, j, min_idx; 
  
    // One by one move boundary of 
    // unsorted subarray 
    for (i = 0; i < n - 1; i++) { 
        // Find the minimum element in 
        // unsorted array 
        min_idx = i; 
        for (j = i + 1; j < n; j++) 
            if (arr[j] < arr[min_idx]) 
                min_idx = j; 
  
        // Swap the found minimum element 
        // with the first element 
        swap(&arr[min_idx], &arr[i]); 
    } 
}





void main(){
	elevatorAlgo(9);
}
