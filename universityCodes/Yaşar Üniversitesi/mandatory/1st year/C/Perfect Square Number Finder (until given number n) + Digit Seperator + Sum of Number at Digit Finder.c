#include <stdio.h>
#include <stdbool.h>

bool digitSumPerfectSquare (int k)
{
	int m;
	m = k;
	int sum = 0;
	for (; k != 0; )
	{
		sum = sum + k%10;
		k = k/10;
	}
	
	int i;
	for (i=1; i<=m; i++)
	{
		if (i*i== sum)
		{
			return true;
		}
	}
	
	return false;
}

int sumPrinter (int k)
{
	int m;
	m = k;
	int sum = 0;
	for (; k != 0; )
	{
		sum = sum + k%10;
		k = k/10;
	}
	return sum;
}

int DigitByDigitPrinter (int k)
{
	int sum = 0;
	int counter = 0;
	int digit;
	for (; k != 0; )
	{
		digit = k%10;
		k = k/10;
		counter++;
		if (counter==1)
		printf ("%d" , digit);
		
		else 
		printf (":%d" , digit);
	}
	printf (" ");
	return sum;
}


int main ()
{
	int a, i;
	printf ("Sayi giriniz: ");
	scanf ("%d" , &a);
	
	for (i=0; i<a; i++)
	{
		if (digitSumPerfectSquare(i))
		{
		printf ("%d " , i);
		DigitByDigitPrinter (i);	
		printf ("%d \n" , sumPrinter (i));
		
	}
}
}
