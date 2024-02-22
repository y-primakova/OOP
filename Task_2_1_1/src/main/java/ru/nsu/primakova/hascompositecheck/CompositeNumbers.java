package ru.nsu.primakova.hascompositecheck;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Class CompositeNumbers.
 */
public abstract class CompositeNumbers {
    /**
     * Checks if the number is composite.
     *
     * @param num - number.
     * @return true if number is composite.
     */
    public static boolean isComposite(int num) {
        if (num == 1) {
            return true;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean isCompositeInArray(List<Integer> arr) throws InterruptedException, ExecutionException;
}
