#include <stdio.h>
#include <string.h>

#define PASSWORD_LENGTH 5

int isValid(char password[PASSWORD_LENGTH]){
	
	FILE* fp = fopen("password.txt", "r");
	char* string;
	fscanf (fp , "%[^\n]", *string);
	
	fclose(fp);
	
	return strcmp(password, string);
}


int bruteForce(){
	
	char password_trial[PASSWORD_LENGTH];
	
	char chr1, chr2, chr3, chr4, chr5;
	
	for(chr1 = '0'; chr1<='9'; chr1++){
		 chr2 = '0';
		for(chr2 = '0'; chr2<='9'; chr2++){
			chr3= '0';
			for(chr3 = '0'; chr3<='9'; chr3++){
				chr4= '0';
				for(chr4 = '0'; chr4<='9'; chr4++){
					chr5= '0';
					for(chr5 = '0'; chr5<='9'; chr5++){
						password_trial[0] = chr1;
						password_trial[1] = chr2;
						password_trial[2] = chr3;
						password_trial[3] = chr4;
						password_trial[4] = chr5;
						
						if( isValid(password_trial) == 0){
							printf("%s", password_trial);
							return 1;
						}
							
					}
				}
			}
		}
	}
	
	
	//isvalid kullan
	
	return 0;
}


void main()
{
	
	
	bruteForce();
	


}


