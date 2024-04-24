package ru.nsu.primakova;

import static ru.nsu.primakova.Json.writeJson;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Main.
 */
public class Main {
    /**
     * main.
     */
    public static void main(String[] args) {
        var commandPrompt = new CommandPrompt();
        var command = new String[]{"-path", "notebook.json"};
        commandPrompt.parse(command);
        if (!Files.exists(Path.of(commandPrompt.getpath()))) {
            List<Note> notes = new ArrayList<>();
            writeJson(notes, commandPrompt.getpath());
        }
        var command1 = new String[]{"-show"};
        commandPrompt.parse(command1);

    }
}




