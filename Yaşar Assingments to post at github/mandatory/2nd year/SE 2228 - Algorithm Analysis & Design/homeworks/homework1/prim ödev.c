#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <limits.h>
#define INFINITY 0
#define SIZE graph->num_vertices


////// Report about time complexity of project:

	/*In function "prims_algorithm", there are 3 inner loops (starting from line 201 and ending at line 285)
		Since this part has the highest complexity, other parts does not have any impact on complexity
				Instead of "SIZE", I will say "n"
			 
		first loop (201th line): this loop runs n times, since i iterates until i<n
		
		second loop (217th line): this loop first runs 1 times, then 2 times, then 3 times, and it's amount of running increases 1 times at each iteration of n
			This creates a complexity of O(n) -> since, if it would run until j<n, it's complexity would still be O(n)
		
		third loop (221th line): this loop also runs n times, since it runs until i<n is false
		
		O(n*n*n) = O(n^3)
			
*/


typedef struct {
	int source;
	int destination;
	int weight;
} EDGE;

typedef struct{
	int num_vertices;
	int num_edges;
	EDGE *edges;
} GRAPH;

GRAPH *init_graph(int vertices, int edges);
void fill_graph(GRAPH *graph);
int** graphIntoMatrix(GRAPH *graph);
void prims_algorithm(GRAPH *graph);
bool checkNotCycle(int* arr, int size, int elementSearched);

GRAPH *init_graph(int vertices, int edges) {
	GRAPH *graph = calloc(1, sizeof(*graph));
	if(graph == NULL) {
		printf("Error:");
		exit(EXIT_FAILURE);
	}
	
	graph->num_vertices = vertices;
	graph->num_edges = edges;
	graph->edges = calloc(edges, sizeof(EDGE));
	
	if(graph->edges == NULL){
		printf("Error:");
		exit(EXIT_FAILURE);
	}
	return graph;
}

void fill_graph(GRAPH *graph){
	graph->edges[0].source = 1;
	graph->edges[0].destination = 3;
	graph->edges[0].weight = 12;

	graph->edges[1].source = 1;
	graph->edges[1].destination = 2;
	graph->edges[1].weight = 4;

	graph->edges[2].source = 2;
	graph->edges[2].destination = 5;
	graph->edges[2].weight = 10;

	graph->edges[3].source = 3;
	graph->edges[3].destination = 5;
	graph->edges[3].weight = 2;

	graph->edges[4].source = 3;
	graph->edges[4].destination = 4;
	graph->edges[4].weight = 7;

	graph->edges[5].source = 4;
	graph->edges[5].destination = 5;
	graph->edges[5].weight = 3;

	graph->edges[6].source = 4;
	graph->edges[6].destination = 6;
	graph->edges[6].weight = 8;

	graph->edges[7].source = 5;
	graph->edges[7].destination = 6;
	graph->edges[7].weight = 9;

	graph->edges[8].source = 6;
	graph->edges[8].destination = 7;
	graph->edges[8].weight = 11;

	graph->edges[9].source = 6;
	graph->edges[9].destination = 8;
	graph->edges[9].weight = 1;

	graph->edges[10].source = 7;
	graph->edges[10].destination = 8;
	graph->edges[10].weight = 13;

	graph->edges[11].source = 7;
	graph->edges[11].destination = 10;
	graph->edges[11].weight = 6;

	graph->edges[12].source = 8;
	graph->edges[12].destination = 9;
	graph->edges[12].weight = 5;

	graph->edges[13].source = 9;
	graph->edges[13].destination = 10;
	graph->edges[13].weight = 14;
}

void prims_algorithm(GRAPH *graph){
	// TODO: Implement the algorithm.
	// TODO: Print the solutioın.
	
	int** matrixGraph = graphIntoMatrix(graph);
	
	
	int mst_arrSize = 1;
	EDGE* mst_arr = calloc(mst_arrSize, sizeof(EDGE));
	mst_arr[0] = graph->edges[1]; 
	

	matrixGraph[graph->edges[1].source - 1][graph->edges[1].destination - 1] = INFINITY;
	
	int solutionSourceArrSize  = 2;
	int* solutionSourceArr = (int*)calloc(solutionSourceArrSize, sizeof(int));
	solutionSourceArr[0] = mst_arr[0].source;
	solutionSourceArr[1] = mst_arr[0].destination;
	







	int a,b;
	bool notFound = true;
	int matrixAllPossibleSourcesArr[graph->num_vertices];
	int nextIndex = 0;
	
	for(a=0; a<graph->num_vertices; a++){
		matrixAllPossibleSourcesArr[a] = 0;
	}
	
	
	for(a=0; a<graph->num_edges; a++){
		 notFound = true;
		for(b=0; matrixAllPossibleSourcesArr[b]!=0; b++){
			if(graph->edges[a].source == matrixAllPossibleSourcesArr[b]){
				notFound = false;
				break;
			}
		}
		if(notFound){
			matrixAllPossibleSourcesArr[nextIndex] = graph->edges[a].source;
			nextIndex++;                         
		}
		
	}
	printf("matrixAllPossibleSourcesArr: ");
	for(a=0; a<graph->num_vertices; a++){
		printf("%d ", matrixAllPossibleSourcesArr[a]);
	}
	printf("\n\n");
	
	
	
	
	
	
		for(a=0; a<graph->num_edges; a++){
		 notFound = true;
		 
		for(b=0; b<graph->num_vertices; b++){
			
			if(graph->edges[a].destination == matrixAllPossibleSourcesArr[b]){
				notFound = false;
				break;
			}
		}
		if(notFound){
			matrixAllPossibleSourcesArr[nextIndex] = graph->edges[a].destination;
			nextIndex++;                         
		}
		
	}
		printf("matrixAllPossibleSourcesArr: ");
	for(a=0; a<graph->num_vertices; a++){
		printf("%d ", matrixAllPossibleSourcesArr[a]);
	}
	printf("\n\n");
	
		
		

	int i,j,k;
	
	bool edgeMinFound = false;
	
	for(i=0; i<SIZE; i++){
		EDGE min;
		
	    edgeMinFound = false;
		
		if(i==2){
		    int s = 1;
		}
	

		
		for(j=0; j<solutionSourceArrSize; j++){
		
			for(k=0; k<SIZE; k++){
				if(matrixGraph[solutionSourceArr[j] -1][k] != INFINITY){
				    
				    if(!edgeMinFound && checkNotCycle(solutionSourceArr, solutionSourceArrSize, matrixAllPossibleSourcesArr[k] )){
				        min.weight =  matrixGraph[solutionSourceArr[j] -1][k];
						min.source = solutionSourceArr[j]; 
						min.destination = matrixAllPossibleSourcesArr[k]; 
						edgeMinFound = true;
				    }
				    
				    else{
				        	if(matrixGraph[solutionSourceArr[j] -1][k] <= min.weight){ 

						if( checkNotCycle(solutionSourceArr, solutionSourceArrSize, matrixAllPossibleSourcesArr[k] ) ){
							
							
							
							min.weight =  matrixGraph[solutionSourceArr[j] -1][k];
							min.source = solutionSourceArr[j]; 
							min.destination = matrixAllPossibleSourcesArr[k]; 
				

						}
					}
				    }
				
						
					
				}
					

			}
		}
		printf("\n\nIteration: %d\n\n", i);
	    
	    if(edgeMinFound){
	        	mst_arrSize++; 
		mst_arr = realloc(mst_arr, (mst_arrSize*sizeof(EDGE) ));
		
		solutionSourceArrSize++; 
		solutionSourceArr = realloc(solutionSourceArr, (solutionSourceArrSize*sizeof(int) ) );
		
		
		
		mst_arr[i+1] = min;
		solutionSourceArr[i+2] = min.destination; 
		matrixGraph[min.source - 1][min.destination -1] = INFINITY;
		
	    }
	
	
		
		for(k=0; k<mst_arrSize; k++){
		printf("%d.  %d - %d ->  %d\n", i, mst_arr[k].source, mst_arr[k].destination, mst_arr[k].weight);
	}
	printf("\nSolution arr's elements:\n");
	for(k=0; k<solutionSourceArrSize; k++){
		printf("%d ", solutionSourceArr[k] );
	}
	printf("\n\n");
	
	int l;
	
	for(k=0; k<SIZE; k++){
			for(l=0; l<SIZE; l++){
			    if(matrixGraph[k][l] == INFINITY)
		        printf("- ");
		        else
		         printf("%d ", matrixGraph[k][l] );
	}
	printf("\n");
}

}
}

int** graphIntoMatrix(GRAPH *graph){


	
	 int** matrix=(int **)calloc(SIZE ,sizeof(int*));
	 int a;
	 
      for(a=0;a<SIZE ;a++)
      {
          matrix[a]=(int *)calloc(SIZE ,sizeof(int));
      }
	
	int i,j;
	for(i=0; i<SIZE; i++){
		for(j=0; j<SIZE; j++){
			matrix[i][j] = INFINITY;
		}
	}
	
	for(i=0; i<graph->num_edges; i++)
	{
		matrix[graph->edges[i].source-1][graph->edges[i].destination-1] = graph->edges[i].weight;
	}
	
	
	for(i=0; i<SIZE; i++)
	{
		for(j=0; j<SIZE; j++)
		{
			if(matrix[i][j] == INFINITY)
				printf("- ");
	
			else
			printf("%d ", matrix[i][j]);
		}
		printf("\n");
	}

		
		
		return matrix;
	}

bool checkNotCycle(int* arr, int size, int elementSearched){
	int i;
	for(i = 0; i<size; i++){
		if(arr[i] == elementSearched)
			return false;
	}
	return true;
	
}

int main() {
	GRAPH *graph = init_graph(10, 14);


	fill_graph(graph);
	
	prims_algorithm(graph);
	return 0;
}
