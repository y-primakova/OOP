package ru.nsu.primakova.hascompositecheck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Main.
 */
public class Main {

    /**
     * main.
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        int[] arr = {3, 17, 11, 5, 29, 8, 23};
        var list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        var a = new Consistent();
        System.out.println(a.isCompositeInArray(list));
        var b = new ParallelStream();
        System.out.println(b.isCompositeInArray(list));
        var c = new Parallel();
        System.out.println(c.isCompositeInArray(list));
        ForTests.testFile(200);
    }
}