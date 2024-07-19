#include <iostream>

using namespace std;


template<class T>
class TreeNode {
public:
    T data;
    int frequency;
    string path;
    TreeNode *left;
    TreeNode *right;

    TreeNode(T data, int frequency) {
        this->data = data;
        this->frequency = frequency;
        this->left = NULL;
        this->right = NULL;
        this->path = "";
    }
};

template<class T>
class HuffmanTree {
public :
    HuffmanTree(TreeNode<T> **sortedNodeList, int size) {
        this->sortedNodeList = sortedNodeList;
        this->size = size;
    }

    // Use the mergeNodes function and sortedNoteList to construct a huffman tree.
    // You can type function calls by yourself instead of using for/while loops.
    TreeNode<T> *buildTree() {
		TreeNode<T> *tmp1 = mergeNodes(sortedNodeList[0], sortedNodeList[1]);
		TreeNode<T> *tmp2 = mergeNodes(sortedNodeList[2], sortedNodeList[3]);
		TreeNode<T> *tmp3 = mergeNodes(sortedNodeList[4], tmp1);
		TreeNode<T> *tmp4 = mergeNodes(sortedNodeList[5], sortedNodeList[6]);
		TreeNode<T> *tmp5 = mergeNodes(tmp2, tmp3);
		TreeNode<T> *tmp6 = mergeNodes(tmp4, tmp5);		
		
		
		return tmp6;
    }

    // Use one of the tree traversal methods to assign correct path value to the leaf nodes
    // As an example, the node with value 's' should have a path as "00" and
    // the node with value 'q' should have a path "1110"
    void assignPaths(TreeNode<T> *tempRoot, string path) {
		
		if(tempRoot->right!= NULL){
			assignPaths(tempRoot->right, path + "1");
		}
		tempRoot->path = path;
		
		if(tempRoot->left!= NULL){
		assignPaths(tempRoot->left, path + "0");
	}
    	
    }

    void clear(TreeNode<T> *tempRoot) {
        if (tempRoot != NULL) {
            clear(tempRoot->left);
            clear(tempRoot->right);
            delete tempRoot;
        }
    }

private :
    TreeNode<T> **sortedNodeList;
    int size;

    // merge the given node values and return a new node connection
    // merged node data can be NULL
    TreeNode<T> *mergeNodes(TreeNode<T> *leftChild, TreeNode<T> *rightChild) {
    	TreeNode<T> *parent = new TreeNode<T>(NULL , leftChild->frequency + rightChild->frequency);
    	parent->left = leftChild;
    	parent->right = rightChild;
    	
    	return parent;
    }


};
