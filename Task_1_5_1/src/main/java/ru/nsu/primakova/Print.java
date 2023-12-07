package ru.nsu.primakova;

/**
 * Print.
 */
public class Print {
    public static void print(String str) {
        try {
            Double.parseDouble(str);
            System.out.println(" = " + str);
        } catch (NumberFormatException e) {
            System.out.println(str);
        }
    }
}
