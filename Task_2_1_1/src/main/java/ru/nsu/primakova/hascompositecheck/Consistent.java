package ru.nsu.primakova.hascompositecheck;

import java.util.List;

/**
 * Class Consistent.
 */
public class Consistent extends CompositeNumbers {
    @Override
    public boolean isCompositeInArray(List<Integer> arr) {
        return arr.stream().anyMatch(CompositeNumbers::isComposite);
    }
}
