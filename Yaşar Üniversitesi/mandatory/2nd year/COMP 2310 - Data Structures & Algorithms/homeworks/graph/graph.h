#include <iostream>
//////////////// 21070006054- ARDA HARMAN
//////////////// 21070006054- ARDA HARMAN
//////////////// 21070006054- ARDA HARMAN
//////////////// 21070006054- ARDA HARMAN
//////////////// 21070006054- ARDA HARMAN

using namespace std;

template<class T>
class Vertex {
public:
    string name;
    T data;
    Vertex<T> *next;

    Vertex(string name, T data) {
        this->name = name;
        this->data = data;
        this->next = NULL;
    }
};

template<class T>
class Edge {
public:
    Vertex<T> *initialVertex;
    int connectionCount;

    Edge(Vertex<T> *initialVertex) {
        this->initialVertex = initialVertex;
        connectionCount = 0;
    }
};


template<class T>
class Graph {
public:
    int verticesCount;
    Vertex<T> **vertices;
    Edge<T> **edges;

    Graph(int verticesCount) {
        this->verticesCount = verticesCount;
        vertices = new Vertex<T> *[verticesCount];
    }


    /*
      Create edges by using the information on slide 22 and int main,
      instead of 1,2,3,4 you will use City A,City B,City C... City F vertices
      Store all the edges in an edge array
    */
    void createEdges(string edgeVerticesName[][2], int edgeNameCount) {
        // Allocate the necessary space for each vertex in the graph to represent edges
        edges = new Edge<T> *[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            edges[i] = new Edge<T>(vertices[i]);
        }
        
        for(int i=0; i<verticesCount; i++){
        	for(int j = i+1; j<edgeNameCount; j++){
        		
        		if(edges[i]->initialVertex->name == getEdgeByInitialVertexName(edgeVerticesName[j][0])->initialVertex->name) {
        			
        			if(edges[i]->initialVertex->next == NULL){
        				edges[i]->connectionCount++;
        				edges[i]->initialVertex->next = getVertexByName(edgeVerticesName[j][1]);
					}
					else{
						Vertex<T> *tmp = edges[i]->initialVertex;
						
						for(int k = 0; k<edges[i]->connectionCount; k++){
							cout<<"connection count: "<< edges[i]->connectionCount <<endl;
							tmp = tmp->next;
						}
						
						
						tmp->next = getVertexByName(edgeVerticesName[j][1]);
						tmp->next->next = NULL;
						edges[i]->connectionCount++;
						
					}
        			
					
				}
			}
		}
    }

    /*
       Create Vertex by assigning the names in verticesName as vertex's name
       and assigning the last character of the name as data
       If the name in the list is equal to City A
       Vertex name should be "City A"
       Vertex data should be 'A'
       Finally store all the vertex in vertices array
   */
    void createVertices(string *verticesNames, int verticesSize) {
    	int i;
		
		for(i=0; i<verticesSize; i++){
			vertices[i] = new Vertex<T>(verticesNames[i], verticesNames[i].back() );
		}
    }

    void printGraph() {
        Edge<T> *edge;
        Vertex<T> *vertex;
        cout << "Graph Info " << endl;
        for (int i = 0; i < verticesCount; i++) {
            edge = edges[i];
            vertex = edge->initialVertex;
            for (int count = 0; count <= edge->connectionCount; count++) {
                cout << vertex->name << "->";
                vertex = vertex->next;
            }
            cout << endl;
        }
        cout << endl;
    }

    void clear() {
        for (int i = 0; i < verticesCount; i++) {
            delete vertices[i];
        }
        for (int i = 0; i < verticesCount; i++) {
            delete edges[i];
        }
    }

    // Search and return the vertex with the name given as parameter
    Vertex<T> *getVertexByName(string name) {
		int i;
		for(i=0; i<verticesCount; i++){
			if(vertices[i]->name == name)
				return vertices[i];
		}
    }

    // Search and return the edge with the name of the vertex given as parameter
    // Perform a linear search
    Edge<T> *getEdgeByInitialVertexName(string name) {
		int i;
		for(i=0; i<verticesCount; i++){
			if(edges[i]->initialVertex->name == name)
				return edges[i];
		}
    }
    // This is testing only function, do not use,edit or change it
    Edge<T>** getEdgeList(){
        return edges;
    }

};
