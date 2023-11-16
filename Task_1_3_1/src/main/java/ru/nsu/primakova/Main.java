package ru.nsu.primakova;



import java.io.IOException;
import java.util.ArrayList;

/**
 * Class Main.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        var list = new ArrayList<Integer>();
        list = SubstringSearch.read("src/main/resources/input_rus.txt", "бра");
        System.out.println(list.toString());
    }
}