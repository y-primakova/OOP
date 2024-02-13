package ru.nsu.primakova;

/**
 * Class CompositeNumbers.
 */
public class CompositeNumbers {
    public static boolean isComposite(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }
}
