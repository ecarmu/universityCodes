#include <stdio.h>

int pow (int k, int m)
{
	int i;
	int result = 1;
	
	for (i=0; i<m; i++)
	{
		result = result * k;
	} 
	return result;
}


int main ()
{
	int digit_count = 1;
	int a, b, c;
	printf ("Please enter a number: ");
	scanf ("%d" , &a);
	
	b = a;
	c = b;
	
	for (; a/10 != 0; )
	{
		a = a/10;
		digit_count = digit_count + 1;
	}
	
	int sum = 0;
	for (; b!=0; )
	{
		sum = sum + pow (b%10 , digit_count);
		b = b/10;
	}
	
	if (sum == c)
	printf ("\n\n%d is an Armstrong number" , c);
	
	else printf ("\n\n%d is not an Armstrong number" , c);
}
