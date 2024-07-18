#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "someAlogrithm.h"

int countLines(FILE *fp)
{
    int count = 0;
    char c;
    while ((c = fgetc(fp)) != EOF)
    {
        if (c == '\n')
        {
            count++;
        }
    }
    return count;
}

char **readFile(char *fileName, int n)
{
    FILE *fp = fopen(fileName, "r");
    if (fp == NULL)
    {
        printf("Error opening file");
        exit(1);
    }

    // count the number of lines in the file
    int count = n;
    // allocate memory for the **wordsay of strings
    char **words = (char **)malloc(count * sizeof(char *));

    int i = 0;
    // find wordlength each line allocate memory for each word
    int wordLength = 0;
    char c;
    for (c = getc(fp); c != EOF; c = getc(fp))
    {
        if (c == '\n')
        {
            words[i] = (char *)malloc(wordLength * sizeof(char));
            i++;
            wordLength = 0;
        }
        else
        {
            wordLength++;
        }
    }

    // read the file again and store the strings in the **wordsay
    rewind(fp);
    i = 0;
    int j = 0;
    for (c = getc(fp); c != EOF; c = getc(fp))
    {
        if (c == '\n')
        {
            words[i][j] = '\0';
            i++;
            j = 0;
        }
        else
        {
            words[i][j] = c;
            j++;
        }
    }

    fclose(fp);
    return words;
}

void writeToFile(char *fileName, char **words, unsigned long long n)
{
    FILE *fp = fopen(fileName, "w");
    if (fp == NULL)
    {
        printf("Error opening file");
        exit(1);
    }
	
	int i;
    for (i = 0; i < n; i++)
    {
        fprintf(fp, "%s\n", words[i]);
    }

    fclose(fp);
}

void calculateTime(void (*func)(char **, int), char **words, int n)
{
    clock_t start = clock();
    func(words, n);
    clock_t end = clock();
    double time = (double)(end - start) / CLOCKS_PER_SEC;

    printf("Time taken: %f seconds\n", time);
}

void generateAlgoReport(void (*func)(char **, int),
                        char *name,
                        char **words,
                        int wordSize,
                        char writeFile)
{

    // add new string **wordsays to not overwrite the original ones
    char **wordsCopy = (char **)malloc(wordSize * sizeof(char *));

    // copy the original **wordsays
    int i;
    for (i = 0; i < wordSize; i++)
    {
        wordsCopy[i] = (char *)malloc(strlen(words[i]) * sizeof(char));
        strcpy(wordsCopy[i], words[i]);
    }

    printf("%s\n-----------------------------------\n", name);

    calculateTime(func, wordsCopy, wordSize);

    printf("-----------------------------------\n\n");

    if (writeFile)
    {

        // with output folder
        char *folder = "output/";
        char *fileName = (char *)malloc(strlen(folder) + strlen(name) + strlen(".txt") + 1);

        strcpy(fileName, folder);
        strcat(fileName, name);
        strcat(fileName, ".txt");
        fileName[strlen(folder) + strlen(name) + strlen(".txt")] = '\0';

        writeToFile(fileName, wordsCopy, wordSize);
    }

    // free the memory
    
    for (i = 0; i < wordSize; i++)
    {
        free(wordsCopy[i]);
    }
    free(wordsCopy);
}

int main()
{
    // You can modify the options here
    char *desc = "Random order sort"; 
    char *fileNameRandom = "data/words.txt";
    char *fileNameAsc = "data/wordsAsc.txt";
    char *fileNameDesc = "data/wordsDesc.txt";
    char writeFile = 1;
    // Try all sorting algorithms
    void *sortingAlgorithms[] = {InsertionSort, HeapSort, QuickSort};
    char *names[] = {"InsertionSort", "HeapSort", "QuickSort"};

    // read the file
    FILE *fp = fopen(fileNameRandom, "r");
    FILE *fpAsc = fopen(fileNameAsc, "r");
    FILE *fpDesc = fopen(fileNameDesc, "r");
    if (fp == NULL || fpAsc == NULL || fpDesc == NULL)
    {
        printf("Error opening file");
        exit(1);
    }

    unsigned long long int n = countLines(fp);

    char **words = readFile(fileNameRandom, n);
    char **wordsAsc = readFile(fileNameAsc, n);
    char **wordsDesc = readFile(fileNameDesc, n);


    // check names and sortingAlgorithms **wordsay length if not equal then exit
    unsigned int namesLen = sizeof(names) / sizeof(names[0]);
    unsigned int sortingAlgorithmsLen = sizeof(sortingAlgorithms) / sizeof(sortingAlgorithms[0]);

    if (namesLen != sortingAlgorithmsLen)
    {
        printf("Error: names and sortingAlgorithms **wordsay length not equal");
        exit(1);
    }

    // generate report for each algorithm
    int i;
    for (i = 0; i < sortingAlgorithmsLen; i++)
    {
        // Asceding string creation
        char *ascName = (char *)malloc(strlen(names[i]) + strlen(" Ascending") + 1);
        strcpy(ascName, names[i]);
        strcat(ascName, " Ascending");
        ascName[strlen(names[i]) + strlen(" Ascending")] = '\0';

        // Descending string creation
        char *descName = (char *)malloc(strlen(names[i]) + strlen(" Descending") + 1);
        strcpy(descName, names[i]);
        strcat(descName, " Descending");
        descName[strlen(names[i]) + strlen(" Descending")] = '\0';

        // Random
        generateAlgoReport(sortingAlgorithms[i], names[i], words, n, writeFile);
        // Ascending
        generateAlgoReport(sortingAlgorithms[i], ascName, wordsAsc, n, writeFile);
        // Descending
        generateAlgoReport(sortingAlgorithms[i], descName, wordsDesc, n, writeFile);

        // free the memory
        free(ascName);
        free(descName);
    }

    // Free words
    for (i = 0; i < n; i++)
    {
        free(words[i]);
    }
    free(words);

    return 0;
}
