package ru.nsu.primakova;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Json.
 */
public class Json {
    public static ArrayList<Note> read(String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filepath), new TypeReference<ArrayList<Note>>() {});
        } catch (IOException e) {
            System.out.println("Read failed.");
            return null;
        }
    }

    public static void write(ArrayList<Note> notes, String filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filepath), notes);
        } catch (IOException e) {
            System.err.print("Write failed.");
        }
    }
}
