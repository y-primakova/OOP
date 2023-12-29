package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.primakova.Json.readJson;
import static ru.nsu.primakova.Json.writeJson;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * JsonTest.
 */
public class JsonTest {
    @Test
    public void testRead() {
        var filepath = "src/test/resources/testReadJson.json";
        var res = readJson(filepath);
        assertEquals("title1", res.get(0).gettitle());
        assertEquals("text1", res.get(0).gettext());
        assertEquals("27.12.2023 23:06", res.get(0).getdate());
        assertEquals("title2", res.get(1).gettitle());
        assertEquals("text2", res.get(1).gettext());
        assertEquals("27.12.2023 23:07", res.get(1).getdate());
    }

    @Test
    public void testWrite() {
        var filepath = "src/test/resources/testWriteJson.json";
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("title1", "text1"));
        writeJson(notes, filepath);
        var res = readJson(filepath);
        assertEquals(notes.get(0).gettitle(), res.get(0).gettitle());
        assertEquals(notes.get(0).gettext(), res.get(0).gettext());
        assertEquals(notes.get(0).getdate(), res.get(0).getdate());
    }
}
