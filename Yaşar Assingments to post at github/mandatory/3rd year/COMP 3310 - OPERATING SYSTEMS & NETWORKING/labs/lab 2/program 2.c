#include<stdio.h>
#include<pthread.h>
int num = 0;
pthread_mutex_t lock;

void* print1(void* data)
{
 
 char *str;
 str = (char*)data;
 
 pthread_mutex_lock(&lock);
 while(num <= 4 && num!= 5)
 {
 	
 printf("%s - %d\n",str, num++);
 
 
 }
 pthread_mutex_unlock(&lock); 
}

void* print2(void* data)
{
 
 char *str;
 str = (char*)data;
 
pthread_mutex_lock(&lock);
 while(num >= 5 && num <= 9 && num!= 10)
 {
 	
 printf("%s - %d\n",str, num++);
 
 
 }
 pthread_mutex_unlock(&lock);
}


/*

 */

void main()
{
 pthread_t t1,t2;
 pthread_mutex_init(&lock, NULL);
 pthread_create(&t1,NULL,print1,"Thread 1");
 pthread_create(&t2,NULL,print2,"Thread 2");
 pthread_join(t1,NULL);
 pthread_join(t2,NULL);
}
