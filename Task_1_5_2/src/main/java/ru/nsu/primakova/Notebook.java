package ru.nsu.primakova;

import java.util.ArrayList;

/**
 * Notebook.
 */
public class Notebook {
    private final ArrayList<Note> notes;

    public Notebook(Note note) {
        this.notes = new ArrayList<>();
        this.notes.add(note);
    }

    public Notebook(ArrayList<Note> note) {
        this.notes = new ArrayList<>();
        this.notes.addAll(note);
    }
}
