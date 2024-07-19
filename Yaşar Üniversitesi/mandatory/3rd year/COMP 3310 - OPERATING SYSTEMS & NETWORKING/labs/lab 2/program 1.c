#include<stdio.h>
#include<pthread.h>
int num = 0;
pthread_mutex_t lock;

void* print(void* data)
{
 
 char *str;
 str = (char*)data;
 while(num <= 9)
 {
 	pthread_mutex_lock(&lock);
 printf("%s - %d\n",str, num++);
 sleep(1);
 pthread_mutex_unlock(&lock);
 }
 
 sleep(2);
}

void main()
{
 pthread_t t1,t2;
 pthread_mutex_init(&lock, NULL);
 pthread_create(&t1,NULL,print,"Thread 1");
 pthread_create(&t2,NULL,print,"Thread 2");
 pthread_join(t1,NULL);
 pthread_join(t2,NULL);
}
