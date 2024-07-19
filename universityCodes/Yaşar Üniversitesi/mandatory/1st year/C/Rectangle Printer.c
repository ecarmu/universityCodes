#include <stdio.h>
#define SCREEN_SIZE 20


void createScreen (int arr [SCREEN_SIZE][SCREEN_SIZE])
{
	int i,j;
	for (i=0; i<SCREEN_SIZE; i++)
	{
		for (j=0; j<SCREEN_SIZE; j++)
		{
			arr [i][j] = 0;
		} 
	}
	
}


void drawRectangle (int arr [SCREEN_SIZE][SCREEN_SIZE], int row_initial, int column_initial, int rectangle_height, int rectangle_width)
{
	int i,j;
	for (i=row_initial; i<rectangle_height; i++)
	{
		for (j=column_initial; j<rectangle_width; j++)
		{
			if (i==row_initial || i==rectangle_height-1 || j==column_initial || j==rectangle_width-1)
			{
				arr[i][j] = 1;
			}
			else arr [i][j] = 0;
		}	
	}
}


void fillRectangle (int arr [SCREEN_SIZE][SCREEN_SIZE], int row_initial, int column_initial, int rectangle_height, int rectangle_width)
{
	int i,j;
	for (i=row_initial; i<rectangle_height; i++)
	{
		for (j=column_initial; j<rectangle_width; j++)
		{
			if (arr [i][j] == 0)
			{
			arr [i][j] = 1;
			}
		}	
	}
}

void drawHline (int arr [SCREEN_SIZE][SCREEN_SIZE], int row_initial, int column_initial, int length)
{
	int i,j;
	for (j=column_initial; j<column_initial + length; j++)
	{
		arr [row_initial][j] = 1;
	}
}

void drawVLine (int arr [SCREEN_SIZE][SCREEN_SIZE], int row_initial, int column_initial, int length)
{
	int i,j;
	for (i=row_initial; i<row_initial + length; i++)
		{
			arr [i][column_initial] = 1;
		}
}

void clearScreen (int arr [SCREEN_SIZE][SCREEN_SIZE])
{
	int i,j;
	for (i=0; i<SCREEN_SIZE; i++)
	{
		for (j=0; j<SCREEN_SIZE; j++)
		{
			if (arr [i][j] == 1)
			{
				arr [i][j] = 0;
			}
		}
	}
}


void printScreen(int arr [SCREEN_SIZE][SCREEN_SIZE])
{
	int i,j, k,m;
	for(i = 0; i < SCREEN_SIZE; i++)
	{	
		for (k=0; k<7500000; k++){
		}
		for (j = 0; j < SCREEN_SIZE; j++)
		{
			for (m=0; m<7500000; m++){
			}
			if(i == 0 || i == SCREEN_SIZE -1)
			printf("- ");
			else if(j == 0 || j == SCREEN_SIZE - 1)
			printf("| ");
			else if (arr[i][j] == 0)
			printf("  ");
			else if (arr[i][j] == 1)
			printf("* ");
		
		}
		printf("\n");
	}
}





void main ()
{
	int a;
	printf ("Merhaba...   Kendimi tanitayim: Ben,  *' li DIKDORTGEN YAPICI...\n\n");
	printf ("Simdi, size   < 'MAIN' de  >    <  DEFAULT OLARAK GIRILMIS  >    degerlerden dikdortgenler yapacagim\n\n ");
	printf ("   Main' de cagirilan fonksiyonlarin parametrelerini degistirip farkli sekillerde tespit edebilirsiniz....   \n\n\n");
	printf ("--->  KOLAY  GELSIN   <---");
	printf ("\n \n \n \n \n \n \n");
	
	int i,j; 
	int arr [SCREEN_SIZE][SCREEN_SIZE];
	
	
	printf ("DrawRectangle Fonksiyonu: \n");
	createScreen(arr);
	printf ("\n");
	drawRectangle(arr, 1, 1, 5, 8);
	printScreen (arr);
	printf ("\n \n \n \n \n \n");
	
	
	printf ("FillRectangle Fonksiyonu: \n");
	printf ("\n");
	fillRectangle(arr, 1, 1, 5, 8);
	printScreen (arr);	
	printf ("\n \n \n \n \n \n");
	
	
	printf ("DrawHLine Fonksiyonu: \n");
	printf ("\n");
	drawHline(arr, 16, 12, 8);
	printScreen (arr);	
	printf ("\n \n \n \n \n \n");
	
	
	printf ("DrawVLine Fonksiyonu: \n");
	printf ("\n");
	drawVLine(arr, 12, 1, 8);
	printScreen (arr);
}

