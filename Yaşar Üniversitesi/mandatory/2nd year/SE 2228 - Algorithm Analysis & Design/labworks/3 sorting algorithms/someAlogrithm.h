// do sorting with ascending order you can use any amount of functions 
// But dont change parameter and return type of this functions

// Do big o analyis use T(n) for time, finally calculate
// big O of your algorithm
#include <string.h>

void str_swap(char** str1,char** str2) {
    char* temp = *str1;
    *str1 = *str2;
    *str2 = temp ; 
}

void InsertionSort(char **words, int n) {
	int i,j;
	
	for(i=0; i<n-1; i++){          // T(n)
		
		int indexOfMin = i;         // T(n*1)
		
		for(j=i+1; j<n; j++){           // T(n*n)
			
			if( strcmp(words[j] , words[indexOfMin]) < 0){
				indexOfMin = j;     // T(n*n*1)
			}
			
			
			
		}
		str_swap(&words[indexOfMin], &words[i]);         // T(n*n*1)
		
	}
}
// 1. Big O analysis someAlgorithm1

//   O(n*n) = T(n) + T(n*1) + T(n*n) + T(n*n*1) + T(n*n*1)

/*       Both best and worst cases are O(n*n)        


*/



void heapify(char **words, int n, int i) 
{
        int largest = i;          //T(1)
        int left = 2 * i + 1;	//T(1)
        int right = 2 * i + 2;    //T(1)
        
        if (left < n && strcmp(words[left], words[largest])>0)          //T(1)
                largest = left;     //T(1)
         
        if (right < n && strcmp(words[right], words[largest])>0)      //T(1)
                largest = right;        //T(1)
        
        
        if (largest != i)
            {
                str_swap(&words[i], &words[largest]);  
                heapify(words, n, largest);   //T(logn)
            }
    //total complexity of heapify is T(logn)
}

void HeapSort(char **words, int n) {
	
	int i;
	
	for (i = n / 2 - 1; i >= 0; i--)     //T(n)
        heapify(words, n, i);     //T(n*logn)
    for (i = n - 1; i >= 0; i--) {     //T(n)
        str_swap(&words[0], &words[i]);
        heapify(words, i, 0);   //T(n*logn)
    }
     //total complexity of heapify is T(n*logn)
}




// 2. Big O analysis someAlgorithm2
//O(n*logn) = 7*T(n) + T(2*n*logn) + T(2*n)




void quickSort(char** arr, int beg, int end){
	if(end > beg + 1){    //T(1)
		char pivot[strlen(arr[beg])];   //T(1)
		strcpy(pivot, arr[beg]);     //T(1)
		
		int l = beg +1;    //T(1)
		int r = end;   //T(1)
		while(l<r){     
			if(strcmp(arr[l], pivot) <= 0)
				l++;
			else
				str_swap(&arr[l], &arr[--r]);	 //T(1)
		}
		str_swap(&arr[--l], &arr[beg]);   //T(1)
		quickSort(arr,beg,l);    //T( (n/2)*logn)
		quickSort(arr,r,end);	 //T( (n/2)*logn)
		
	}

}




void QuickSort(char **words, int n) {
	quickSort(words, 0, n-1);  //T(n*logn)

}
// 3. Big O analysis someAlgorithm3
//O(n*logn) = 7*T(1) + 2*T( (n/2)logn)



/*  Pivot starts from the beginning. This makes the algoryhtm fast enough for ascending order sorting. 
Also, for random sorting it is really fast 
But, for descending order it works poorly. Because, for this situation, it is the WORST CASE. Because, it works n*n times. We select a pivot n times, which every time pivot traverses the array n times
We don't select a pivot n times in other cases, it is selected logn times
*/


// Questions and Answers write minimum 1-2 sentences for each question

// 1. Which algorithm is the fastest for ascending order? Why?
//Heap sort. Because heap is a tree which starts from root to leaves. If heap is sorted, there won't be any operations to heapify which makes it really quick

// 2. Which algorithm is fastest for random order? Why?
//Quick sort. Because it is selecting a pivot randomly which makes it to work best with not sorted arrays
