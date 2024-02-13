package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class TestParallel.
 */
public class TestParallel {
    @Test
    public void test1() throws WrongNumberException, InterruptedException {
        int[] arr = {6, 8, 7, 13, 5, 9, 4};
        assertTrue(Parallel.isCompositeInArray(arr, 1));
    }

    @Test
    public void test2() throws WrongNumberException, InterruptedException {
        int[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertFalse(Parallel.isCompositeInArray(arr, 1));
    }

    @Test
    public void test3() throws WrongNumberException, InterruptedException {
        int[] arr = {121};
        assertTrue(Parallel.isCompositeInArray(arr, 1));
    }

    @Test
    public void test4() throws WrongNumberException, InterruptedException {
        int[] arr = {29};
        assertFalse(Parallel.isCompositeInArray(arr, 1));
    }

    @Test
    public void test5() throws WrongNumberException, InterruptedException {
        int[] arr = {19, 37, 83};
        assertFalse(Parallel.isCompositeInArray(arr, 1));
    }

    @Test
    public void test6() throws WrongNumberException, InterruptedException {
        int[] arr = {};
        assertFalse(Parallel.isCompositeInArray(arr, 1));
    }
}
