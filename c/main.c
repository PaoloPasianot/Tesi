#include <stdio.h>
#include <time.h>

// Funzione iterativa per calcolare il n-esimo numero di Fibonacci
int fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    int a = 0, b = 1, temp;
    for (int i = 2; i <= n; i++) {
        temp = a + b;
        a = b;
        b = temp;
    }
    return b;
}

// Funzione principale
int main() {
    printf("init");

    clock_t start, end;
    double cpu_time_used;

    start = clock();

    int n = 2147483647;
    for (int i = 0; i < n; i++) {
        fibonacci(i);
    }

    // Fine del cronometro
    end = clock();
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;

    printf("\nTempo di esecuzione: %f secondi\n", cpu_time_used);
    printf("end");
    return 0;
}