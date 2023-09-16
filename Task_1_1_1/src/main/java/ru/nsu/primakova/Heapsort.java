package ru.nsu.primakova;

public class Heapsort {
    public static void main(String[] args) {
    }
    public static int[] heapsort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapS(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int a = arr[0];
            arr[0] = arr[i];
            arr[i] = a;

            heapS(arr, i, 0);
        }
        return arr;
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