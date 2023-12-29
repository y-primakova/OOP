package ru.nsu.primakova;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Json.
 */
public class Json {
    /**
     * read .json file.
     *
     * @param filepath - path to the file
     * @return list of notes
     */
    public static List<Note> readJson(String filepath) {
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filepath), new TypeReference<List<Note>>() {});
        } catch (IOException e) {
            System.out.println("Read failed.");
            return null;
        }
    }

    /**
     * write .json file.
     *
     * @param notes - list of notes
     *
     * @param filepath - path to the file
     */
    public static void writeJson(List<Note> notes, String filepath) {
        var objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filepath), notes);
        } catch (IOException e) {
            System.out.print("Write failed.");
        }
    }
}
