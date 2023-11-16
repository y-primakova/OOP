package ru.nsu.primakova;
import java.util.Arrays;

import static java.util.Arrays.copyOf;

public class Heapsort {
    public static int[] heapsort(int[] arr) {
        int n = arr.length;
        int[] arr_sort = copyOf(arr, n);

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapS(arr_sort, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int a = arr_sort[0];
            arr_sort[0] = arr_sort[i];
            arr_sort[i] = a;

            heapS(arr_sort, i, 0);
        }
        return arr_sort;
    }

    private static void heapS(int[] arr, int n, int i) {
        int indMax = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[indMax]) {
            indMax = l;
        }
        if (r < n && arr[r] > arr[indMax]) {
            indMax = r;
        }
        if (indMax != i) {
            int a = arr[i];
            arr[i] = arr[indMax];
            arr[indMax] = a;

            heapS(arr, n, indMax);
        }
    }
}