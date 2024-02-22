package ru.nsu.primakova.hascompositecheck;

import java.util.List;

/**
 * Class Parallel.
 */
public class Parallel extends CompositeNumbers {
    private final int numberThread;

    public Parallel() {
        this.numberThread = 1;
    }

    /**
     * Constructor.
     *
     * @param numberThread - number of threads.
     */
    public Parallel(int numberThread) {
        if (numberThread < 1) {
            this.numberThread = 1;
        } else {
            this.numberThread = numberThread;
        }
    }

    @Override
    public boolean isCompositeInArray(List<Integer> arr) throws InterruptedException {
        int x = arr.size() / this.numberThread;
        var thread = new MyThread[this.numberThread];
        int indThread = 0;
        for (int i = 0; i < this.numberThread - 1; i++) {
            thread[indThread] = new MyThread(arr.subList(i * x, (i + 1) * x));
            thread[indThread].start();
            indThread++;
        }
        thread[indThread] = new MyThread(arr.subList((this.numberThread - 1) * x, arr.size()));
        thread[indThread].start();

        for (int i = 0; i < this.numberThread; i++) {
            thread[i].join();
            if (thread[i].get_res()) {
                return true;
            }
        }
        return false;
    }

    public static class MyThread extends Thread {
        List<Integer> arr;
        boolean res;

        public MyThread(List<Integer> arr) {
            this.arr = arr;
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
