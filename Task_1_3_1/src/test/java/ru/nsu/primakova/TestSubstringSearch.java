package ru.nsu.primakova;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class TestSubstringSearch.
 */
public class TestSubstringSearch {
    @Test
    public void test_rus() {
        var list = new ArrayList<Long>();
        list = SubstringSearch.read("src/main/resources/input_rus.txt", "бра");
        var actual = new ArrayList<Long>();
        actual.add(1L);
        actual.add(8L);

        assertEquals(list, actual);
    }

    @Test
    public void test_eng() {
        var list = new ArrayList<Long>();
        list = SubstringSearch.read("src/main/resources/input_eng.txt", "bra");
        var actual = new ArrayList<Long>();
        actual.add(1L);
        actual.add(8L);

        assertEquals(list, actual);
    }

    @Test
    public void test() {
        var list = new ArrayList<Long>();
        list = SubstringSearch.read("src/test/resources/test.txt", "⻃⻌");
        var actual = new ArrayList<Long>();
        actual.add(1L);
        actual.add(3L);
        actual.add(7L);

        assertEquals(list, actual);
    }

    public void testBigFile() {
        var file = new File("file");
        try(var f = new RandomAccessFile(file, "rw")) {
            f.setLength(1024L * 1024L * 1024L * 15L);
            f.seek(1024L * 1024L * 1024L * 15L);
            f.writeBytes("hi");
            var list = SubstringSearch.read("file", "hi");
            var actual = new ArrayList<>();
            actual.add(1024L * 1024L * 1024L * 15L);
            assertEquals(list, actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            file.delete();
        }
    }
}
