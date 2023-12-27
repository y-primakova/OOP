package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.primakova.Json.readJson;
import static ru.nsu.primakova.Json.writeJson;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * CommandPromptTest.
 */
public class CommandPromptTest {
    @Test
    public void testAdd() {
        var filepath = "src/test/resources/testCommandPrompt.json";
        List<Note> notes = new ArrayList<>();
        writeJson(notes, filepath);
        var commandPrompt = new CommandPrompt(filepath);
        var command1 = new String[]{"-add", "title1", "text1"};
        commandPrompt.parse(command1);
        var res = readJson(filepath);
        assertEquals("title1", res.get(0).gettitle());
        assertEquals("text1", res.get(0).gettext());
    }

    @Test
    public void testRm() {
        var filepath = "src/test/resources/testCommandPrompt.json";
        List<Note> notes = new ArrayList<>();
        writeJson(notes, filepath);
        var commandPrompt = new CommandPrompt(filepath);
        var command1 = new String[]{"-add", "title1", "text1"};
        commandPrompt.parse(command1);
        var command2 = new String[]{"-add", "title2", "text2"};
        commandPrompt.parse(command2);
        var command3 = new String[]{"-rm", "title1"};
        commandPrompt.parse(command3);
        var res = readJson(filepath);
        assertEquals("title2", res.get(0).gettitle());
        assertEquals("text2", res.get(0).gettext());
    }
}
