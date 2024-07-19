#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*DO NOT CHANGE ANY PARAMETER, INCLUDES, STRUCTURES
  DO NOT ADD EXTRA FUNCTIONS, DO NOT CHANGE main and createGraph
  DO NOT ADD EXTRA INCLUDES
  SEND ONLY "graph.txt" and "kruskalLabwork.c
  FILL createUniques, unionFind, kruskalMST and sortEdges FUNCTIONS"
  */

// Edge inside the graph
typedef struct Edge
{
    unsigned int weight;
    unsigned char begin;
    unsigned char end;
} Edge;

// Graph structure
typedef struct Graph
{
    unsigned int size;
    Edge *edges;
} Graph;

// create Graph from file
Graph *createGraph(char *filename)
{
    // open file in read mode 
    FILE *fp = fopen(filename, "r");

    // check if file is there
    if (fp == NULL)
    {
        printf("File not found");
        exit(1);
    }
    else
    {
        // calculate size of the graph by line number
        // initialize begin end, weight

        // create graph
        Graph *graph = malloc(sizeof(Graph));

        // calculate size of the graph by line number. BE CAREFUL with the last line
        int size = 0;
        char c;
        while ((c = fgetc(fp)) != EOF)
        {
            if (c == '\n')
                size++;
        }
        graph->size = ++size;
        

        // create empty edges by size
        graph->edges = malloc(sizeof(Edge) * size);


        // initialize edges BE CAREFUL about file pointer
        rewind(fp);
        int i;
        for (i = 0; i < size; i++)
        {
            fscanf(fp, "%c%c %d\n", &graph->edges[i].begin, &graph->edges[i].end, &graph->edges[i].weight);
        }

        fclose(fp);
        return graph;
    }
}


char* createUniques(Graph *graph) {

    // create empty letters list 2 times plus 1 (for '\0 last char') the size of the graph
    /* NOTE: Allocate double size for space begin and end of the edges */
    
    // Combine letters from edge begin char and edge end char
    
    // sort letters by bubble sort

    // remove duplicates from letters
    // initialize empty unique
    // DONT FORGET reallocate unique each time

    // Add '\0' at the end of the string

    // free unused variables   

int length = 2*graph->size + 1;
	char* letterArr = (char*) malloc(sizeof(char)* (2*graph->size + 1) );
	
	int i,j;
	for(i=0; i<(length-1)/2; i++){
		letterArr[i] = graph->edges[i].begin;
		letterArr[i + (length-1)/2] = graph->edges[i].end;
	}
	letterArr[length*2] = '\0'; 
	
	
	char tmp = ' ';
	i=0;
	while(letterArr[i]!='\0'){
		for(j=0; j<graph->size*2; j++){
			
			if(letterArr[j] > letterArr[j+1]){
				tmp = letterArr[j];
				letterArr[j] = letterArr[j+1];
				letterArr[j+1] = tmp; 
			}
		}
		i++;
	}
		//(yeni arr aç ve -üstteki- 2. for loop da o arr'ý doldur) 3. bir for loop ekle ve sonra, orda uniques in içini doldur
	// for loop'lara graph->edges[i]!= '\0' þartýný ekle
	int counter = 0;
	
	char* tmp_arr = (char*) malloc(sizeof(char)*length);
	
	for(i=0; i<length-1; i++){
		
			
			if(letterArr[i]!= letterArr[i+1]){
			tmp_arr[counter] = letterArr[i];
			counter++;
			}
	}
		
		
	char* uniques = (char*) malloc(sizeof(char)*length);	
	
	for(i=0; i<length-1; i++){
		*(uniques+i) = *(tmp_arr+i);
	}
	
	free(letterArr);
	realloc(uniques, counter);
	
	while(i<graph->size-1){
		printf("%c\n", uniques[i]);
		i++;
	}
	
    return uniques;
}

// Union find function
/* unionFind is for checking if the edge is a cycle. 
If it is a cycle, do not add it to the minimum spanning tree */
char* unionFind(char *uniques, char cno1, char cno2, unsigned int len)
{
	
	int i;
	for(i = 0; i<len; i++){
		if(uniques[i] == cno2)
			uniques[i] = cno1;
	}
		
    return uniques;
}

// kruskal mst algorithm
Graph *kruskalMST(Graph *graph)
{
	
    Graph *mst = (Graph*) malloc(sizeof(Graph));
    mst->size = 0;
    mst->edges = (Edge*) malloc(sizeof(Edge));


    char* uniques = createUniques(graph);
    int length = strlen(uniques);

    char cno1;
    char cno2;
    int indexcno1;
    int indexcno2;    

    int counter = 0;

	int i,k;
	
    for(i = 0; i<graph->size; i++){


        indexcno1 = graph->edges[i].begin - 'A';
        indexcno2 = graph->edges[i].end - 'A';
        cno1 = uniques[indexcno1];
        cno2 = uniques[indexcno2];


        if(cno1 != cno2)
        {     
			uniques = unionFind(uniques,cno1,cno2,length);
			
            mst->size++;
            mst->edges = realloc(mst->edges, sizeof(Edge)*mst->size);
            
            
            mst->edges[counter] =graph->edges[i];
            counter++;

        }
        
		printf("%d.)cno1: %c %d   cno2: %c %d ", i, cno1, indexcno1, cno2,indexcno2);
 		
 		for(k = 0; k<length; k++)
 			printf("%c", uniques[k]);
		printf("\n");
 

    }




    return mst;
    

}




// sort edges by weight use bubble sort
Graph * sortEdges(Graph *graph)
{
	int i,j;
	int tmp;
	char tmpchr;
	for(i=0; i<graph->size -1; i++){
		for(j=0; j<graph->size -1 - i; j++){
			
			if(graph->edges[j].weight > graph->edges[j+1].weight){
				tmp = graph->edges[j].weight;
				graph->edges[j].weight = graph->edges[j+1].weight;
				graph->edges[j+1].weight = tmp; 
				
				tmpchr = graph->edges[j].begin;
				graph->edges[j].begin = graph->edges[j+1].begin;
				graph->edges[j+1].begin = tmpchr;
				
				tmpchr = graph->edges[j].end;
				graph->edges[j].end = graph->edges[j+1].end;
				graph->edges[j+1].end = tmpchr;
			}
		}
	}

	for(i=0; i<graph->size; i++)
		printf("%c%c %d\n", graph->edges[i].begin, graph->edges[i].end, graph->edges[i].weight);
    return graph;
}

int main()
{
    // create graph;
    Graph *graph = createGraph("graph.txt");
    
    // sort graph for kruskal algorithm when getting minimum weight
    graph = sortEdges(graph);
    
    
    
    // kruskal minimum spanning tree
    Graph *mst = kruskalMST(graph);

    // print minimum spanning tree
    int i;
    for (i = 0; i < mst->size; i++)
    {
        printf("%d.) %c%c %d\n",(i+1), mst->edges[i].begin, mst->edges[i].end, mst->edges[i].weight);
    }

    // calculate total weight of the minimum spanning tree
    int totalWeight = 0;
    for (i = 0; i < mst->size; i++)
    {
        totalWeight += mst->edges[i].weight;
    }
    printf("Total weight of the minimum spanning tree: %d\n", totalWeight);

    // dont forget to free memory otherwise you will get memory leak
    free(graph);
    free(mst);

    return 0;
}
