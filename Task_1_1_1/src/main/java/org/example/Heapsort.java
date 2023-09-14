package org.example;

public class Heapsort {
    public static void main(String[] args) {
        int[] arr = {1, 6, 7, -5, 9, 0, 3};
        heapsort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    public static void heapsort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int a = arr[0];
            arr[0] = arr[i];
            arr[i] = a;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int max = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[max]) {
            max = l;
        }
        if (r < n && arr[r] > arr[max]) {
            max = r;
        }
        if (max != i) {
            int a = arr[i];
            arr[i] = arr[max];
            arr[max] = a;

            heapify(arr, n, max);
        }
    }
}
