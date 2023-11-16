package ru.nsu.primakova;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class SubstringSearch.
 */
public class SubstringSearch {
    public static ArrayList<Integer> read(String filename, String subString) throws IOException {
        var res = new ArrayList<Integer>();
        if (subString == null) {
            return res;
        }
        String substr = new String(subString.getBytes(), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
        int c;
        int indexSub = 0;
        int indexAll = 0;
        while((c = reader.read()) != -1) {
            char character = (char) c;
            if (substr.charAt(indexSub) == character) {
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
    }
}
