package ru.nsu.primakova;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ru.nsu.primakova.Json.writeJson;

/**
 * Main.
 */
public class Main {
    /**
     * main.
     */
    public static void main(String[] args) {
        var filepath = "notebook.json";
        if (!Files.exists(Path.of(filepath))) {
            List<Note> notes = new ArrayList<>();
            writeJson(notes, filepath);
        }
        var commandPrompt = new CommandPrompt(filepath);
        var command1 = new String[]{"-show"};
        commandPrompt.parse(command1);
    }
}




