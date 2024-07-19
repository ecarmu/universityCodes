#include <windows.h>
#include <string.h>
#include <stdio.h>
#include <stdbool.h>

int createFile()
{
FILE *fp;
fp = fopen("myfile.txt", "w");
fprintf(fp, "%s\n", "Hello World, This is a test.");
fclose(fp);
system("pause");

writeToFile();

return 0;
}

int writeToFile ()
{
FILE * pFile;
char c;
char name_arr[4] = {"ARDA"};
char no_arr[11] = {"21070006054"};

pFile = fopen ("myfile.txt","w");
if (pFile!=NULL)
{
	int i = 0;
	int a = 0;
	
	for(i = 0; i < 10; i++){
		for (a = 0 ; a < 4 ; a++)
			fputc ( (int) name_arr[a] , pFile );
		fputc ( ' ' , pFile );
			
		for (a = 0 ; a < 11 ; a++)
			fputc ( (int) no_arr[a] , pFile );	
		fputc ( '\n' , pFile );
	}

fclose (pFile); }
system("pause");
return 0; 
}

bool checkFileStatus() {
FILE *fp;
fp = fopen("myfile.txt", "a");
if (fp==NULL)
return false;
else {
fprintf(fp, "%s\n", "Testing append mode.");
fclose(fp);}
system("pause");
return true; 
}


void main(){
	// check file status
	if(!checkFileStatus() ){
		// if does not exist, create the file AND THEN open new created file
		createFile();
		return ;
	}
	

	// if does exist, just open existing file	
	// write to the file
	else
	writeToFile();
	
}
