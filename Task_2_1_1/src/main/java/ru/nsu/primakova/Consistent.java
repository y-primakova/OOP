package ru.nsu.primakova;

/**
 * Class Consistent.
 */
public class Consistent {
    public static boolean isCompositeInArray(int[] arr) {
        for (var num : arr) {
            if (CompositeNumbers.isComposite(num)) {
                return true;
            }
        }
        return false;
    }
}
