#include <stdio.h>
#include <stdbool.h>

int maxNeededToWork[5][5] = {
	{7, 5, 3, 3, 6},
	{3, 2, 2, 2, 3},
	{9, 0, 2, 2, 2},
	{4, 3, 3, 3, 4},
	{5, 3, 3, 3, 5}
};
int maxAllocated[5][5] = {
	{0, 1, 0, 0, 0},
	{2, 0, 0, 1, 1},
	{3, 0, 2, 1, 0},
	{0, 0, 2, 2, 1},
	{0, 0, 3, 0, 0}
};

int available[5] = {5, 4, 3, 4, 7};
int total[5] = {10, 5, 10, 8, 9};

int safeSequence[5];


void bankersAlgo(){
	int i, j;
	int counter = 0;
	
	int size = sizeof(maxNeededToWork) / sizeof(maxNeededToWork[0]);
	
	bool satisfyNeed;
	
	while(counter < 5) {
		
		if(counter == 0)
		printStep();
		
		satisfyNeed = false;
		
		
		for(i = 0; i < size; i++){
			
			satisfyNeed = true;
			
			
			for(j = 0; j < size; j++){
				
				if(available[j] + maxAllocated[i][j] < maxNeededToWork[i][j] || maxAllocated[i][j] == -1 ){
					satisfyNeed = false;
					break;
				}
					
			}
			
			if(satisfyNeed){
				int z;
				
				for(z = 0; z < size; z++){
					available[z] += maxAllocated[i][z]; 
					maxAllocated[i][z] = -1;
				}
				safeSequence[counter] = i;
				break;
			}
				
			
		}
		
		counter++;
		printStep();
	}
	
	printSequence();
	
}

void printStep(){
	int i, j;
	int size = sizeof(maxNeededToWork) / sizeof(maxNeededToWork[0]);
	
	for(i = 0; i < size; i++){
		for(j = 0; j < size; j++){
			printf("%d ",maxAllocated[i][j]);
		}
		
		printf("\n");
		
	}
	
		printf("\n\n\n\n");
}

void printSequence(){
	int i = 0;
	int size = sizeof(maxNeededToWork) / sizeof(maxNeededToWork[0]);
	
	for(; i < size; i ++){
		if(i == 0)
		printf("%d", safeSequence[i]);
		else
		printf(" -> %d", safeSequence[i]);
	}
	printf("\nSystem is safe");
}


void main(){
	
	bankersAlgo();
}
