package ru.nsu.primakova;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ru.nsu.primakova.hascompositecheck.Consistent;
import ru.nsu.primakova.hascompositecheck.Parallel;
import ru.nsu.primakova.hascompositecheck.ParallelStream;

/**
 * Class Main.
 */
public class Main {

    /**
     * main.
     */
    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
        var reader = new BufferedReader(new FileReader("test"));
        var currentLine = reader.readLine();
        reader.close();
        var list = Stream.of(currentLine.substring(1, currentLine.length() - 1).split(", ")).map(Integer::parseInt).collect(Collectors.toList());

        long startTime;
        long endTime;

        var a = new Consistent();
        System.out.print("Consistent: ");
        startTime = System.currentTimeMillis();
        System.out.print(a.isCompositeInArray(list) + " ");
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        var b = new ParallelStream();
        System.out.print("ParallelStream: ");
        startTime = System.currentTimeMillis();
        System.out.print(b.isCompositeInArray(list) + " ");
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        System.out.println("Parallel:");
        for (int i = 1; i <= 64; i *= 2) {
            Parallel c;
            c = new Parallel(i);
            System.out.print(i + " ");
            startTime = System.currentTimeMillis();
            System.out.print(c.isCompositeInArray(list) + " ");
            endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime);
        }
    }
}