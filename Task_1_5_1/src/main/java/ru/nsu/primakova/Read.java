package ru.nsu.primakova;

import java.util.Scanner;

/**
 * Read.
 */
public class Read {
    public static String readNextLine() {
        var scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }
}
