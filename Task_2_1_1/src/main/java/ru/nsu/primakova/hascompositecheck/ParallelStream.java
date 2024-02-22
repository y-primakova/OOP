package ru.nsu.primakova.hascompositecheck;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * Class ParallelStream.
 */
public class ParallelStream extends CompositeNumbers {
    private final int numberThread;

    public ParallelStream() {
        this.numberThread = 1;
    }

    /**
     * Constructor.
     *
     * @param numberThread - number of threads.
     */
    public ParallelStream(int numberThread) {
        if (numberThread < 1) {
            this.numberThread = 1;
        } else {
            this.numberThread = numberThread;
        }
    }

    @Override
    public boolean isCompositeInArray(List<Integer> arr) throws ExecutionException, InterruptedException {
        var pool = new ForkJoinPool(this.numberThread);
        var res = pool.submit(() ->
                arr.parallelStream().anyMatch(CompositeNumbers::isComposite)).get();
        pool.shutdown();
        return res;
    }
}
