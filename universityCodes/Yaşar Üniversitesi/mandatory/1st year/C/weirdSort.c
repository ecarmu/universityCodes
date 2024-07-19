#include <stdio.h>

void swap(int arr[], int size1, int size2)
{
    int tmp;
    tmp = arr[size1];
    arr[size1] = arr[size2];
    arr[size2] = tmp;
}

void weirdSort (int arr[] , int size)
{
    int tmp, i, j;
    for (i = 0; i < size; i = i+2)
    {
        for(j = i + 2; j < size; j = j + 2)
        {
            if(arr[i] > arr[j])
            {
                swap(arr,i,j);
            }
        }
        
    }
	for (i = 1; i < size; i = i+2)
    {
        for(j = i + 2; j < size; j = j + 2)
        {
            if(arr[i] < arr[j])
            {
                swap(arr,i,j);
            }
        }

    }

    printf ("\n");
}

void readArray (int arr [] , int size)
{
	int i;
	for (i=0; i<size; i++)
	{
		int number;
		printf("\n%d. number of array: ", i+1);
		scanf ("%d" , &number);
		arr [i] = number; 
	}
}



void printArray (int arr [] , int size)
{
	printf ("Output of printArray:\n");
	int i;
	for (i=0; i<size; i=i+1)
	{
		printf ("%d " , arr [i]);
	}
	printf ("\n");
}


void weirdPrint (int arr [] , int size)
{
	printf ("Output of weirdPrint:\n");
	int i;
	for (i=0; i<size; i=i+2)
	{
		printf ("%d " , arr [i]);
	}

	printf ("\n");
	
	int j;
	for (j=1; j<size; j=j+2)
	{
		printf ("%d " , arr [j]);
	}
}


void main ()
{
	printf("  ---->>  How weirdSort Works??   <<----\n\n\nIf the array is { 10, 5, 2, 7, 3, 9, 11, 6, 8, 13} then after sorting with weirdSort it should be: \n{2, 13, 3, 9, 8, 7, 10, 6, 11, 5} \n\nThe result of printing should be as: \n2 13 3 9 8 7 10 6 11 5\n\nThe result of weird printing should be as:\n2 3 8 10 11\n13 9 7 6 5\n\n\n");
	int arr [10];
	readArray (arr , 10);
	weirdSort (arr , 10);
	 printArray (arr , 10);
	weirdPrint (arr , 10);
}
