#include <stdio.h>
#include <stdlib.h>
#include<stdbool.h>

typedef struct LINKED_LIST_NODE_s *LINKED_LIST_NODE;

typedef struct LINKED_LIST_NODE_s{

	LINKED_LIST_NODE next; /* Do not change order */

	void *data;

} LINKED_LIST_NODE_t[1];



typedef struct LINKED_LIST_s *LINKED_LIST;

typedef struct LINKED_LIST_s{

  	LINKED_LIST_NODE head; /* May overlap with next. */

} LINKED_LIST_t[1], *LINKED_LIST;


// LINKEDLIST IMPLEMENTATION
LINKED_LIST_NODE nodeCreator(void* data){
	LINKED_LIST_NODE node = (LINKED_LIST_NODE)malloc(sizeof(LINKED_LIST_NODE_t));
	node->data = data;
	node->next = NULL;
	
	return node;
}


typedef struct
{
    int source;
    int destination;

} EDGE;

typedef struct
{
    int num_vertices;
    int num_edges;
    EDGE *edges;
} GRAPH;



void insert(LINKED_LIST linkList, LINKED_LIST_NODE node){
	LINKED_LIST_NODE temp = linkList->head;
	if(linkList->head == NULL){
		linkList->head = node;
	}
	else{
		while(temp->next != NULL){
		temp = temp->next;
		}
		temp->next = node;
		node->next = NULL;
	}
	
}

LINKED_LIST linkedListCreator(LINKED_LIST_NODE head){
	LINKED_LIST linkList = (LINKED_LIST)malloc(sizeof(LINKED_LIST_NODE_t));
	linkList->head = head; 
	
	return linkList;
}

void removee(LINKED_LIST linkList, void* data){
	LINKED_LIST_NODE temp = linkList->head;
	
	
	/*bastaki eleman silme
	while(temp->data != data){
		temp = temp->next;
	}
	
	if(linkList->head == temp){
		linkList->head = linkList->head->next;
		temp->next = NULL;
	}
	else{
		temp->next = NULL;
	}
	
	free(temp);*/
	
	/* sondaki eleman silme */
	while(temp->next->data != data){
		temp = temp->next;
	}
	temp->next = NULL;
	free(temp->next);
}


// GRAPH IMPLEMENTATION

GRAPH *createGraph(int vertices, int edges)
{
    GRAPH *graph = calloc(1, sizeof(*graph));
    if (graph == NULL)
    {
        printf("Error:");
        exit(EXIT_FAILURE);
    }

    graph->num_vertices = vertices;
    graph->num_edges = edges;
    graph->edges = calloc(edges, sizeof(EDGE));

    if (graph->edges == NULL)
    {
        printf("Error:");
        exit(EXIT_FAILURE);
    }
    return graph;
}

void fillGraph(GRAPH *graph)
{
    graph->edges[0].source = 1;
    graph->edges[0].destination = 2;

    graph->edges[1].source = 1;
    graph->edges[1].destination = 3;

    graph->edges[2].source = 1;
    graph->edges[2].destination = 4;

    graph->edges[3].source = 2;
    graph->edges[3].destination = 4;

    graph->edges[4].source = 2;
    graph->edges[4].destination = 5;

    graph->edges[5].source = 4;
    graph->edges[5].destination = 3;

    graph->edges[6].source = 4;
    graph->edges[6].destination = 5;
}

int **createMatrix(GRAPH *graph)
{

	int **matrix = (int **)malloc(sizeof(int *) * graph->num_vertices);
	
	int i,j;
	for (i = 0; i < graph->num_vertices; i++)
	{
		matrix[i] = (int *)malloc(sizeof(int) * graph->num_vertices);
	}

	for (i = 0; i < graph->num_vertices; i++)
	{
		for (j = 0; j < graph->num_vertices; j++)
		{
			matrix[i][j] = 0;
		}
	}

	for (i = 0; i < graph->num_edges; i++)
		matrix[graph->edges[i].source - 1][graph->edges[i].destination - 1] = 1;

	return matrix;
}



void printMatrix(int **matrix, int size)
{
	int i,j;
	
	for (i = 0; i < size; i++)
	{
		for (j = 0; j < size; j++)
		{
			printf("%d ", matrix[i][j]);
		}
		printf("\n");
	}
}


// Total complexity : T ( 2|V| + |E| )
void d1st(int** matrix, int size, int indexOfVertex, bool visited[], bool firstVertex){

	// This part is just implemented to display the sorted list more estheticly
	if(visited[indexOfVertex] == false){

		if(firstVertex)
		printf("Vertex %d ", indexOfVertex+1);

		else
		printf(" -> Vertex %d ", indexOfVertex+1);
	}
		

	

	int i= 0;
	bool leaf = true;

	for(; i<size; i++){ //  T( |V| )  -->  this for loop works as much as the amount of vertices
	// V denotes to the amount of vertices
		
		if(matrix[indexOfVertex][i] == 1 && visited[i] == false){
			leaf = false;
			d1st(matrix, size, i, visited, false);  // T( |E| )  -->  this recursive call is done as öuch as the amount of edges
			// E denotes to the amount of edges

			visited[i] = true; 
		}
		
		
			
	}
	if(leaf){
		// printf("Leaf Vertex %d\n", indexOfVertex+1);
		return;
	}
		
	else{
		
	// T( |V| ) --> this for-loop works as much as the amount of vertices
// What it does is that, for a selected vertex, it checkes all vertices for destroying connections
	for(i=0; i<size; i++){
		
		matrix[indexOfVertex][i] = 0;
		
		
	}
		
	}
	
	
	
	
	
}

// Total complexity: T( |V| * (2|V| + |E|) ) = T(|V|*|V| + |E|)
void TopologicSort_DFS(int** matrix, int size, bool visited[]){
	int i;
	bool firsttVertexx = true;

	// This for-loop calls the "d1st" V times
	for(i=0; i<size; i++){  // // T( |V| )
		d1st(matrix, size, i, visited, firsttVertexx); // T( |V| * (2|V| + |E|) )
		firsttVertexx = false;
	}
}





// 2nd topolic sort method -- source remove


// Total complexity: T(|V|*|V|)
int indegree(int** matrix, int size, bool visited[]){
	
	
	bool indegreeIsZero = true;
	
	int i,j;
	// We search the matrix to find that, if we have a vertex which has a indegree of 0

	for(i = 0; i<size; i++){ //T(|V|)
		indegreeIsZero = true;
		
        
		if(visited[i] == true)
			continue;
		
		for(j=0; j<size; j++){     // T(|V|*|V|)

			if(matrix[j][i] == 1){
				indegreeIsZero = false;
				break;
			}
		}
		if(indegreeIsZero == true){
			return i;
		}
		
	}
	printf("ERRRORRRRR!!! NO VERTEX HAS 0 INDEGREE");
	return -1;
	
}

// Total complexity: T(|V|*|V|)
int removeNode(int** matrix, int size, bool visited[], int a){
	
	int indexOfDeleted = indegree(matrix, size, visited); //T(|V|*|V|)
	int i;
	
	//This section is just to print sorted list more esthetically
	if(a==0)
		printf("Vertex %d ",indexOfDeleted + 1);
	else
		printf(" --> Vertex %d ",indexOfDeleted + 1);

	// What it does is that, for a selected vertex, it checkes all vertices for destroying connections
	for(i=0; i<size; i++){
		matrix[indexOfDeleted][i] = 0;
	}
	
	visited[indexOfDeleted] = true;
	
	
	return indexOfDeleted;
}

// Total complexity: T(|V|*|V|*|V|)
void TopologicSort_sourceRemove(int** matrix, int size, bool visited[]){
	int i;
	
	for(i=0; i<size; i++){  // T(|V|*|V|)
		removeNode(matrix, size, visited, i);  // T(|V|*|V|*|V|)
		
	}
}

void main(){/*  ---LINKEDLIST METHODS
	int dataQQ = 3;
	LINKED_LIST_NODE headNode = nodeCreator(&dataQQ);
	LINKED_LIST linkedList = linkedListCreator(headNode);
	
	int dataQ = 5;
	LINKED_LIST_NODE node2 = nodeCreator(&dataQ);
	
	int dataQdjpsjd = 6;
	LINKED_LIST_NODE node3 = nodeCreator(&dataQdjpsjd);
	
	insert(linkedList, node2);
	insert(linkedList, node3);
	
	printf("%d ", *((int*)(linkedList->head->data)));
	
	printf("%d ", *((int*)(linkedList->head->next->data)));
	
	printf("%d ", *((int*)(linkedList->head->next->next->data)));
	
	
	removee(linkedList, &dataQdjpsjd);
	
	printf("%d ", *((int*)(linkedList->head->data)));
	
	printf("%d ", *((int*)(linkedList->head->next->data)));
	
	printf("%d ", *((int*)(linkedList->head->next->next->data)));

	printf("s");*/
	
	
	// Topologic sort - DFS
	printf("Toplogical Sort ---  DFS\n\n");
	GRAPH *graph = createGraph(5, 7);
    fillGraph(graph);
    

    int** matrix = createMatrix(graph);
    bool visited[5] = {false, false, false, false, false};

	
    printMatrix(matrix, graph->num_vertices);
    printf("\nSorted List: \n");

    TopologicSort_DFS(matrix, graph->num_vertices, visited);
    
	printf("\n\nAfter Sorting:\n");
    printMatrix(matrix, graph->num_vertices);
    
    
    // Topologic sort - 2nd method
	printf("\n\n\nToplogical Sort ---  Source Removal method\n\n");
	GRAPH *graph2 = createGraph(5, 7);
    fillGraph(graph2);
    
    int** matrix2 = createMatrix(graph2);
    bool visited2[5] = {false, false, false, false, false};
    printMatrix(matrix2, graph->num_vertices);
    
    /*
    removeNode(matrix2, graph->num_vertices, visited2);
    printMatrix(matrix2, graph->num_vertices);*/
    printf("\nSorted list:\n");
    TopologicSort_sourceRemove(matrix2, graph->num_vertices, visited2);
	printf("\n\nAfter Sorting:\n");
    printMatrix(matrix2, graph->num_vertices);
}