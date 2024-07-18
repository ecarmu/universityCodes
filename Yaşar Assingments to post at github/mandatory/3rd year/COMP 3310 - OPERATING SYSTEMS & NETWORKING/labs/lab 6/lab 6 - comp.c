#include<stdio.h>
#include <stdbool.h>

void main(){
int i, j,tbt=0,nop,ts=0,flag[20];
int from,wt[20],tt[20],bt[20], at[20], pno[20];
bool dikkat[20];
int dur;
float awt,att;

printf("Enter no. of Processes: \n");
scanf("%d",&nop);

for(i=0;i<nop;i++){
wt[i]=tt[i]=0;
printf("Enter the process no, burst time and priority: \n");
scanf("%d",&pno[i]);
scanf("%d",&bt[i]);
scanf("%d",&at[i]);
dikkat[i] = false;
}

// sorting 
for (i = 0; i < nop; i++) {
    int minIndex = -1;  

    for (j = 0; j < nop; j++) {
        if (!dikkat[j]) {
            if (minIndex == -1 || at[j] < at[minIndex]) {
                minIndex = j;
            }
        }
    }

    flag[i] = minIndex;
    dikkat[minIndex] = true;
}


// tt hesap
int sum = 0;
for(i=0;i<nop;i++){
	
	sum += bt[flag[i]];
tt[flag[i]] = sum; 
}


// wt hesap
wt[0] = 0;
for(i=1;i<nop;i++){
wt[flag[i]]=tt[flag[i-1]];

}


// final
printf("\npno     btime    atime    wtime    ttime\n");
for(i = 0; i < nop; i++){
	printf("%d    %d        %d        %d        %d\n", pno[flag[i]], bt[flag[i]], at[flag[i]], wt[flag[i]], tt[flag[i]]);
}


}
