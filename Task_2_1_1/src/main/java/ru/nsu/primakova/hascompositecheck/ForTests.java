package ru.nsu.primakova.hascompositecheck;

import static ru.nsu.primakova.hascompositecheck.CompositeNumbers.isComposite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class ForTests.
 */
public class ForTests {
    /**
     * Creates a list of prime numbers.
     *
     * @param n - number of numbers in list.
     * @throws IOException
     */
    public static void testFile(int n) throws IOException {
        var arr = new ArrayList<Integer>();
        int count = 0;
        for (int i = Integer.MAX_VALUE; i > 0; i -= 2) {
            if (!isComposite(i)) {
                arr.add(i);
                count++;
            } if (count == n) {
               break;
            }
        }
        var writer = new BufferedWriter(new FileWriter("test"));
        writer.write(arr.toString());
        writer.close();
    }
}
