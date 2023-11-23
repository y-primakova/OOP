package ru.nsu.primakova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class SubstringSearch.
 */
public class SubstringSearch {
    public static ArrayList<Long> read(String filename, String subString) {
        var res = new ArrayList<Long>();
        if (subString == null) {
            return res;
        }
        var substr = new String(subString.getBytes(), StandardCharsets.UTF_8);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (var reader = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(filename), StandardCharsets.UTF_8))) {
            int c;
            long indexSub = 0;
            long indexAll = 0;
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                if (substr.charAt((int) indexSub) == character) {
                    if (indexSub == substr.length() - 1) {
                        res.add(indexAll - indexSub);
                        indexSub = -1;
                    }
                } else {
                    indexSub = -1;
                }
                indexSub++;
                indexAll++;
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
