#include <iostream>
// 21070006054 - ARDA HAMAN
using namespace std;

enum Direction {
    LEFT, RIGHT
};

template<class T>
class TreeNode {
public:
    T data;
    TreeNode *left;
    TreeNode *right;

    TreeNode(T data) {
        this->data = data;
        this->left = NULL;
        this->right = NULL;
    }
};

template<class T>
class BST {
private:
    TreeNode<T> *root;

public :
    BST() {
        root = NULL;
    }

    bool isEmpty() {
        return root == NULL;
    }

    TreeNode<T> *getRoot() {
        return root;
    }

    //Insertion Rule:New nodes with a value less than the node in the tree are added left, otherwise right.
    // Print the "Node is not inserted !" error, if you can not insert the node due to duplicate value.
    void iterativeInsert(TreeNode<T> *newNode) {
        if (root == NULL) {
            root = newNode;
        } else {
            bool isInserted = false;
            TreeNode<T> *tempRoot = root;
            while ((!isInserted) && tempRoot != NULL) {
            	
            	
                if (newNode->data < tempRoot->data) {
                	
                	
                	
                    if (tempRoot->left == NULL) {
                        tempRoot->left = newNode;
                        isInserted = true;
                    } else {
                        tempRoot = tempRoot->left;
                    }
                } 
				else if (newNode->data > tempRoot->data) {
					
					
                    if (tempRoot->right == NULL) {
                        tempRoot->right = newNode;
                        isInserted = true;
                    } else {
                        tempRoot = tempRoot->right;
                    }
                }
				else {
                    break;
                }
            }
            if (!isInserted) {
                cout << "Node is not inserted !" << endl;
            }
        }
    }

    //Insertion Rule:New nodes with a value less than the node in the tree are added left, otherwise right.
    // Print the "Node is not inserted !" error, if you can not insert the node.
    void recursiveInsert(TreeNode<T> *tempRoot, TreeNode<T> *newNode) {
    	bool isInserted = false;
    	
        if (root == NULL) {
            root = newNode;
            bool isInserted = true;
        } 
		else {
			if(tempRoot->data > newNode->data){
				if(tempRoot->left != NULL){
					tempRoot = tempRoot->left;
					recursiveInsert(tempRoot, newNode);
				}
				else{
					tempRoot->left = newNode;
					isInserted = true;
				}
					
				
			}
			else if(tempRoot->data < newNode->data){
				if(tempRoot->right != NULL){
					tempRoot = tempRoot->right;
					recursiveInsert(tempRoot, newNode);
				}
				else{
					tempRoot->right = newNode;
					isInserted = true;
				}
				
			}
			else
			cout << "Node is not inserted !" << endl;
				
        }
       
    }

    // The node with search data may not exist.Print the "Node is not exist !" error, if you can not find the node.
    void deleteNode(T searchData) {
		TreeNode<T> *Parent; 
		TreeNode<T> *grandParent;
		TreeNode<T> *tempNode;
		TreeNode<T> *tempNodeNew;
		TreeNode<T> *tempNodeNew2;
		
		bool dataNotfound = true;
		
		/*enum previousDirectionOf_tempNode; //i will assign this to parent
		enum previousDirectionOf_Parent; //i will assign this to GrandParent*/
		
		/*Direction previousDirectionOf_tempNode;
		Direction previousDirectionOf_Parent;*/
		
		tempNode = root;
		
		int const firstIteration = 1;
		int const secondIteration = 2;
		int thirdIteration = 3;
		int counter = 1;
		
		while(tempNode != NULL && dataNotfound){
			
			if(tempNode->data > searchData){
				
				switch (counter){
				case firstIteration:
				Parent = tempNode;   //same as  Parent = root; 
				tempNode = tempNode->left;
				counter++;
				break;
				 	
				default:
					grandParent = Parent;   //same as grandParent = root;
					Parent = tempNode;
					tempNode = tempNode->left;
					counter++;
				}
			
			}
			else if(tempNode->data < searchData){
				switch (counter){				
				case firstIteration:
				Parent = tempNode;   //same as  Parent = root; 
				tempNode = tempNode->right;
				counter++;
				break;
				 	
				default:
					grandParent = Parent;   //same as grandParent = root;
					Parent = tempNode;
					tempNode = tempNode->right;
					counter++;
				}
			}
			else{
				//aradigim seyi bulursam
				//silecegim node'un, sol cocugunun, sag cocuklari             (sol cocugun sag cocugu yoksa, sol cocugu al)          (sürekli en sag cocuga git)
				dataNotfound = false;
				if(tempNode->left != NULL){
					tempNodeNew = tempNode->left;
				
				while(tempNodeNew->right != NULL){
					tempNodeNew2 = tempNodeNew;
					tempNodeNew = tempNodeNew->right;
				}
					
				tempNode->data = tempNodeNew->data;
				tempNodeNew2 = NULL;
				delete tempNodeNew;
				}
				else{
					Parent = NULL;
					delete tempNode;
				}
				
				
			}
		}
		
		
		if(dataNotfound)
		cout << "Node is not EXIST !" << endl;
    }


    // Print the tree in horizontal line
    void print(TreeNode<T> *tempRoot, int gap = 0) {
        if (tempRoot != NULL) {
            print(tempRoot->right, gap + 1);
            for (int i = 0; i < gap; i++) {
                cout << "\t";
            }
            cout << tempRoot->data << endl;
            print(tempRoot->left, gap + 1);
        }
    }

    void clear(TreeNode<T> *tempRoot) {
        if (tempRoot != NULL) {
            clear(tempRoot->left);
            clear(tempRoot->right);
            delete tempRoot;
        }
    }

};
