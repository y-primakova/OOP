package ru.nsu.primakova;

import java.util.Arrays;

/**
 * Class Parallel.
 */
public class Parallel {
    public static boolean isCompositeInArray(int[] arr, int numberThread) throws WrongNumberException, InterruptedException {
        if (numberThread < 1) {
            throw new WrongNumberException("Wrong thread number.");
        }
        int x = arr.length / numberThread;
        var thread = new MyThread[numberThread];
        int indThread = 0;
        for (int i = 0; i < numberThread - 1; i++) {
            thread[indThread] = new MyThread(Arrays.copyOfRange(arr,i * x, (i + 1) * x));
            thread[indThread].start();
            indThread++;
        }
        thread[indThread] = new MyThread(Arrays.copyOfRange(arr,(numberThread - 1) * x, arr.length));
        thread[indThread].start();

        for (int i = 0; i < numberThread; i++) {
            thread[i].join();
            if (thread[i].get_res()) {
                return true;
            }
        }
        return false;
    }

    public static class MyThread extends Thread {
        int[] arr;
        boolean res;

        public MyThread(int[] arr) {
            this.arr = arr;
            this.res = false;
        }

        public boolean get_res() {
            return res;
        }

        @Override
        public void run() {
            for (var num : arr) {
                if (CompositeNumbers.isComposite(num)) {
                    res = true;
                    return;
                }
            }
        }
    }
}
