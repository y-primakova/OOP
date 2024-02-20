package ru.nsu.primakova.hascompositecheck;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * Class TestParallel.
 */
public class TestParallel {
    @Test
    public void Test1() throws IOException, InterruptedException {
        var reader = new BufferedReader(new FileReader("test"));
        var currentLine = reader.readLine();
        reader.close();
        var list = Stream.of(currentLine.substring(1, currentLine.length() - 1).split(", ")).map(Integer::parseInt).collect(Collectors.toList());
        var parl = new Parallel(200);
        assertFalse(parl.isCompositeInArray(list));
    }

    @Test
    public void Test2() throws IOException, InterruptedException {
        var reader = new BufferedReader(new FileReader("test"));
        var currentLine = reader.readLine();
        reader.close();
        var list = Stream.of(currentLine.substring(1, currentLine.length() - 1).split(", ")).map(Integer::parseInt).collect(Collectors.toList());
        list.add(200);
        var parl = new Parallel(200);
        assertTrue(parl.isCompositeInArray(list));
    }

    @Test
    public void test1() throws InterruptedException {
        int[] arr = {3, 17, 11, 4, 5};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new Parallel();
        assertTrue(parl.isCompositeInArray(list));
    }

    @Test
    public void test2() throws InterruptedException {
        int[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new Parallel();
        assertFalse(parl.isCompositeInArray(list));
    }

    @Test
    public void test3() throws InterruptedException {
        int[] arr = {121};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new Parallel();
        assertTrue(parl.isCompositeInArray(list));
    }

    @Test
    public void test4() throws InterruptedException {
        int[] arr = {29};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new Parallel();
        assertFalse(parl.isCompositeInArray(list));
    }

    @Test
    public void test5() throws InterruptedException {
        int[] arr = {19, 37, 83};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new Parallel();
        assertFalse(parl.isCompositeInArray(list));
    }

    @Test
    public void test6() throws InterruptedException {
        int[] arr = {};
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var parl = new Parallel();
        assertFalse(parl.isCompositeInArray(list));
    }
}
