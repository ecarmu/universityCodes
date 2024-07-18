#include <stdio.h>
#include <stdbool.h>

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


bool armstrongFinder (int s)
{
	int digit_count = 1;
	int b, c;
	
	b = s;
	c = b;
	
	for (; s/10 != 0; )
	{
		s = s/10;
		digit_count = digit_count + 1;
	}
	
	int sum = 0;
	for (; b!=0; )
	{
		sum = sum + pow (b%10 , digit_count);
		b = b/10;
	}
	
	if (sum == c)
	{
	printf ("%d\n" , c);
	return true;
    }
    
    else return false;
}

int main ()
{
	int s, e;
	int counter = 0;
	printf ("Determine which interval you wanna look at: \n\n");
	printf ("Enter the starting value: ");
	scanf ("%d" , &s);
	printf ("Enter the ending value: ");
	scanf ("%d" , &e);
	
	printf ("\n\nHere are the values which are Armstrong: \n");
	for (; s<=e; s++)
	{
		if (armstrongFinder (s))
		{
			counter++;
		}
	}
	printf ("\n\nAmount of Armstrong numbers: %d" , counter);
}
