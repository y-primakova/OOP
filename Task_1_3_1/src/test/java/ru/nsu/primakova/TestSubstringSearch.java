package ru.nsu.primakova;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class TestSubstringSearch.
 */
public class TestSubstringSearch {
    @Test
    public void test_rus() throws IOException {
        var list = new ArrayList<Integer>();
        list = SubstringSearch.read("src/main/resources/input_rus.txt", "бра");
        var actual = new ArrayList<Integer>();
        actual.add(1);
        actual.add(8);

        assertEquals(list, actual);
    }

    @Test
    public void test_eng() throws IOException {
        var list = new ArrayList<Integer>();
        list = SubstringSearch.read("src/main/resources/input_eng.txt", "bra");
        var actual = new ArrayList<Integer>();
        actual.add(1);
        actual.add(8);

        assertEquals(list, actual);
    }
}
