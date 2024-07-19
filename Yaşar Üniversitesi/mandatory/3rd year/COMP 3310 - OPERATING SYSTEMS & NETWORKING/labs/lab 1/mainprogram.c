#include <windows.h>
#include <string.h>
#include <stdio.h>
int main(void){
BOOL bRet;
STARTUPINFO si;
PROCESS_INFORMATION pi;
 ZeroMemory(&si,sizeof(si));
si.cb=sizeof(si);
ZeroMemory(&pi,sizeof(pi));
 bRet=CreateProcess(NULL,"yourprogram.exe",NULL,NULL,FALSE,0,NULL,NULL,&si,&pi);
 if(bRet==FALSE) {
printf("Error: %u\n",GetLastError());
return 1;
}
 CloseHandle(pi.hProcess);
CloseHandle(pi.hThread); // close handles to kernel objs
 return 0;
}
