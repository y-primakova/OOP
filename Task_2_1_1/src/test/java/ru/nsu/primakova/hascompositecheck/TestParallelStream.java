package ru.nsu.primakova.hascompositecheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

/**
 * Class TestParallelStream.
 */
public class TestParallelStream {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        int[] arr = {3, 17, 11, 4, 5};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new ParallelStream(2);
        assertTrue(parl.isCompositeInArray(list));
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        int[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new ParallelStream(3);
        assertFalse(parl.isCompositeInArray(list));
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        int[] arr = {121};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new ParallelStream(1);
        assertTrue(parl.isCompositeInArray(list));
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        int[] arr = {29};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new ParallelStream(1);
        assertFalse(parl.isCompositeInArray(list));
    }

    @Test
    public void test5() throws ExecutionException, InterruptedException {
        int[] arr = {19, 37, 83};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new ParallelStream(2);
        assertFalse(parl.isCompositeInArray(list));
    }

    @Test
    public void test6() throws ExecutionException, InterruptedException {
        int[] arr = {};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new ParallelStream(1);
        assertFalse(parl.isCompositeInArray(list));
    }
}
