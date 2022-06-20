#include <stdio.h>



// fibonaccinin hangi halini denemek istersen onun mainin yanýndaki harfi sil 

// Örnek: "Ýki fonksiyonda" halini denemek istersen; "main_a" yý "main" yap



//Ýki fonksiyonda
int fibonacci_a (int a, int b)
{
	a = a + b;
	return a;	
}




int main_a ()
{
	int i, a, k, m;
	scanf ("%d" , &a);
	k = 1;
	m = 1;
	
	for (i=0; i<a; i++)
	{
	k = fibonacci (k , m);
	m = fibonacci (m , k);		
	printf ("%d %d " , k , m);
	}
	return 0;
}



//Recursion ile 
int fibonacci_b (int a, int b, int n)
{
	if (n < 1)
	{
		return 0;
	}
	
	a = a + b;
	printf ("%d " , a);
	b = a + b,	
	printf ("%d " , b); 
	
	n--;
	fibonacci (a, b, n);
	
}


void main_b ()
{
	int n;
	scanf ("%d" , &n);
	fibonacci (1 , 1, n);
}





//Tek fonksiyonda 
int main_c ()
{
	int i, a, k, m;
	scanf ("%d" , &a);
	k = 1;
	m = 1;
	
	for (i=0; i<a; i++)
	{
		k = k + m;
		m = k + m;	
		printf ("%d %d " , k , m);
	}
}




//Pointer ile 
int fibonacci_d (int* a, int* b)
{
	*a = *a + *b;
	*b = *a + *b;
}




int main_d ()
{
	int i, a, k, m;
	scanf ("%d" , &a);
	k = 1;
	m = 1;
	
	for (i=0; i<a; i++)
	{
	fibonacci (&k , &m);		
	printf ("%d %d " , k , m);
	}
} 
