package ru.nsu.primakova.hascompositecheck;

import java.util.List;

/**
 * Class ParallelStream.
 */
public class ParallelStream extends CompositeNumbers {
    private final int numberThread;
    public ParallelStream() {
        this.numberThread = 1;
    }

    public ParallelStream(int numberThread) {
        if (numberThread < 1) {
            this.numberThread = 1;
        } else {
            this.numberThread = numberThread;
        }
    }

    @Override
    public boolean isCompositeInArray(List<Integer> arr) {
        return arr.parallelStream().anyMatch(CompositeNumbers::isComposite);
    }
}
