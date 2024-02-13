package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class TestConsistent.
 */
public class TestConsistent {
    @Test
    public void test1() {
        int[] arr = {6, 8, 7, 13, 5, 9, 4};
        assertTrue(Consistent.isCompositeInArray(arr));
    }

    @Test
    public void test2() {
        int[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertFalse(Consistent.isCompositeInArray(arr));
    }

    @Test
    public void test3() {
        int[] arr = {121};
        assertTrue(Consistent.isCompositeInArray(arr));
    }

    @Test
    public void test4() {
        int[] arr = {29};
        assertFalse(Consistent.isCompositeInArray(arr));
    }

    @Test
    public void test5() {
        int[] arr = {19, 37, 83};
        assertFalse(Consistent.isCompositeInArray(arr));
    }

    @Test
    public void test6() {
        int[] arr = {};
        assertFalse(Consistent.isCompositeInArray(arr));
    }
}
