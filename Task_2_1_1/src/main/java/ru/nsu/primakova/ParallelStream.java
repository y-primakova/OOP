package ru.nsu.primakova;

import java.util.List;

/**
 * Class ParallelStream.
 */
public class ParallelStream {
    public static boolean isCompositeInArray(List<Integer> arr) {
        return arr.parallelStream().anyMatch(CompositeNumbers::isComposite);
    }
}
