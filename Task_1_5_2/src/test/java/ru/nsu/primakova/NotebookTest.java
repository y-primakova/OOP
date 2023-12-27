package ru.nsu.primakova;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * NotebookTest.
 */
public class NotebookTest {

    @Test
    public void testAdd0() {
        var res = new Notebook();
        res.add(null);
        assertEquals(new ArrayList<>(),res.get_notes());
    }

    @Test
    public void testAdd1() {
        var actual = new Notebook(new Note("title","text"));
        var res = new Notebook(new Note("title","text"));
        res.add(new String[]{"one"});
        assertEquals(actual.get_notes().get(0).gettitle(),res.get_notes().get(0).gettitle());
        assertEquals(actual.get_notes().get(0).gettext(),res.get_notes().get(0).gettext());
        assertEquals(actual.get_notes().get(0).getdate(),res.get_notes().get(0).getdate());
    }
    @Test
    public void testAdd2() {
        var actual = new Notebook(new Note("one","two"));
        var res = new Notebook();
        res.add(new String[]{"one","two"});
        assertEquals(actual.get_notes().get(0).gettitle(),res.get_notes().get(0).gettitle());
        assertEquals(actual.get_notes().get(0).gettext(),res.get_notes().get(0).gettext());
        assertEquals(actual.get_notes().get(0).getdate(),res.get_notes().get(0).getdate());
    }

    @Test
    public void testRm() {
        var actual = new Notebook(new Note("title1","text1"));
        var res = new Notebook(new Note("title1","text1"));
        res.addNote(new Note("title2","text2"));
        res.rm(new String[]{"title2"});
        assertEquals(actual.get_notes().get(0).gettitle(),res.get_notes().get(0).gettitle());
        assertEquals(actual.get_notes().get(0).gettext(),res.get_notes().get(0).gettext());
        assertEquals(actual.get_notes().get(0).getdate(),res.get_notes().get(0).getdate());
    }

    @Test
    public void testShow() {
        var actual = new Notebook(new Note("title1","text1"));
        var res = new Notebook(new Note("title1","text1"));
        res.show(null);
        assertEquals(actual.get_notes().get(0).gettitle(),res.get_notes().get(0).gettitle());
        assertEquals(actual.get_notes().get(0).gettext(),res.get_notes().get(0).gettext());
        assertEquals(actual.get_notes().get(0).getdate(),res.get_notes().get(0).getdate());
    }
}
