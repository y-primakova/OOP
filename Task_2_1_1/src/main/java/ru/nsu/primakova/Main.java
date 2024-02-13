package ru.nsu.primakova;

import java.util.Arrays;
import java.util.List;

/**
 * Class Main.
 */
public class Main {
    public static void main(String[] args) throws WrongNumberException, InterruptedException {
        int[] arr = {3, 17, 11, 5, 29, 8, };
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        System.out.println(Consistent.isCompositeInArray(arr));
        System.out.println(ParallelStream.isCompositeInArray(list));
        System.out.println(Parallel.isCompositeInArray(arr, 1));
    }
}