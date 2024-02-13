package ru.nsu.primakova;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class Main.
 */
public class Main {

    /**
     * main.
     */
    public static void main(String[] args) throws WrongNumberException, InterruptedException {
        int[] arr = {3, 17, 11, 5, 29, 8, 1};
        var list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(Consistent.isCompositeInArray(arr));
        System.out.println(ParallelStream.isCompositeInArray(list));
        System.out.println(Parallel.isCompositeInArray(arr, 1));
    }
}