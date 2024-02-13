package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Class TestParallelStream.
 */
public class TestParallelStream {
    @Test
    public void test1() {
        int[] arr = {3, 17, 11, 4, 5};
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        assertTrue(ParallelStream.isCompositeInArray(list));
    }

    @Test
    public void test2() {
        int[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        assertFalse(ParallelStream.isCompositeInArray(list));
    }

    @Test
    public void test3() {
        int[] arr = {121};
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        assertTrue(ParallelStream.isCompositeInArray(list));
    }

    @Test
    public void test4() {
        int[] arr = {29};
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        assertFalse(ParallelStream.isCompositeInArray(list));
    }

    @Test
    public void test5() {
        int[] arr = {19, 37, 83};
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        assertFalse(ParallelStream.isCompositeInArray(list));
    }

    @Test
    public void test6() {
        int[] arr = {};
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        assertFalse(ParallelStream.isCompositeInArray(list));
    }
}
