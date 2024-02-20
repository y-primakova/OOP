package ru.nsu.primakova.hascompositecheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class TestCompositeNumbers.
 */
public class TestCompositeNumbers {
    @Test
    public void test1() {
        assertTrue(CompositeNumbers.isComposite(46));
    }

    @Test
    public void test2() {
        assertTrue(CompositeNumbers.isComposite(4));
    }

    @Test
    public void test3() {
        assertTrue(CompositeNumbers.isComposite(27));
    }

    @Test
    public void test4() {
        assertFalse(CompositeNumbers.isComposite(2));
    }

    @Test
    public void test5() {
        assertFalse(CompositeNumbers.isComposite(17));
    }

    @Test
    public void test6() {
        assertFalse(CompositeNumbers.isComposite(53));
    }
}
