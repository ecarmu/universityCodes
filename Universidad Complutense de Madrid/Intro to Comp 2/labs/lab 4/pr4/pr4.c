/*---------------------------------------------------------------------
**
**  File:
**    pr4.c  19/10/2022
**
**    (c) Daniel Báscones García
**    Introduction to Computers II
**    Facultad de Informática. Universidad Complutense de Madrid
**
**  Purpose:
**    Code file for project 4
**
**-------------------------------------------------------------------*/


#define N 5

//the two N-component vectors
int U[N] = {5, 2, -3, 7, 6};
int V[N] = {6, -1, 1, 0, 3};
//variable to store the result: 1 if U is greater, 0 otherwise
char greater_u;

extern int eucl_dist(int w [], int size);
extern void save(char value, char * location);

/**
 * Function that saves a value in the provided pointer
 */
/*
void save(char value, char * location) {
	*location = value;
}
*/

/**
 * Simple function to multiply two numbers in an iterative way
 */
int mul(int a, int b) {
	int res = 0, sign = 0;
	if (a < 0) {
		sign = 1;
		a = -a;
	}
	while (a--) res += b;
	if (sign)
		return -res;
	else
		return res;
}

/**
 * Simple function that calculates the square root in an iterative way
 */
int i_sqrt(int a) {
	int root = 0;
	while (mul(root, root) < a) {
		root++;
	}
	return root;
}

/**
 * The euclidean distance is calculated. All the squares are added and finally
 * the (integer) square root is applied.
 */
/*
int eucl_dist(int w [], int size) {
	int acc = 0;
	for (int i = 0; i < size; i++) {
		acc += mul(w[i], w[i]);
	}
	return i_sqrt(acc);
}
*/

/**
 * Entry point to the program
 */
void main() {
	//the euclidean distance to the origin is calculated
	int d_u = eucl_dist(U, N);
	int d_v = eucl_dist(V, N);
	//we check if U was farther
	char greater = d_u > d_v;
	save(greater, &greater_u);

	while(1); //the program remains in an infinite loop
}
