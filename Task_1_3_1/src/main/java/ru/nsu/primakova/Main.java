package ru.nsu.primakova;

import java.util.ArrayList;

/**
 * Class Main.
 */
public class Main {
    public static void main(String[] args) {
        var list = new ArrayList<Long>();
        list = SubstringSearch.read("src/main/resources/input_rus.txt", "бра");
        System.out.println(list.toString());
    }
}