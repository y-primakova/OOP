package ru.nsu.primakova;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.HashMap;

/**
 * Class Json.
 */
public class Json {
    /**
     * read .json file.
     *
     * @param filepath - path to the file
     * @return Config
     */
    public static Config readJson(String filepath) {
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filepath), new TypeReference<Config>() {});
        } catch (IOException e) {
            System.out.println("Read failed.");
            return null;
        }
    }

    /**
     * read .json file.
     *
     * @param filepath - path to the file
     * @return Deque
     */
    public static Deque<Integer> readJsonDeque(String filepath) {
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filepath), new TypeReference<Deque<Integer>>() {});
        } catch (IOException e) {
            System.out.println("Read failed.");
            return null;
        }
    }

    /**
     * read .json file.
     *
     * @param filepath - path to the file
     * @return HashMap
     */
    public static HashMap<Integer, Double> readJsonMap(String filepath) {
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filepath), new TypeReference<HashMap<Integer, Double>>() {});
        } catch (IOException e) {
            System.out.println("Read failed.");
            return null;
        }
    }

    /**
     * write .json file.
     *
     * @param notes - Config
     *
     * @param filepath - path to the file
     */
    public static void writeJson(Config notes, String filepath) {
        var objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filepath), notes);
        } catch (IOException e) {
            System.out.print("Write failed.");
        }
    }

    /**
     * write .json file.
     *
     * @param notes - Deque
     *
     * @param filepath - path to the file
     */
    public static void writeJson(Deque<Integer> notes, String filepath) {
        var objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filepath), notes);
        } catch (IOException e) {
            System.out.print("Write failed.");
        }
    }

    /**
     * write .json file.
     *
     * @param notes - HashMap
     *
     * @param filepath - path to the file
     */
    public static void writeJson(HashMap<Integer, Double> notes, String filepath) {
        var objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filepath), notes);
        } catch (IOException e) {
            System.out.print("Write failed.");
        }
    }
}
