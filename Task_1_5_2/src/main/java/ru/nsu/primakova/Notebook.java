package ru.nsu.primakova;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Notebook.
 */
public class Notebook {
    private List<Note> notes;

    public Notebook() {
        this.notes = new ArrayList<>();
    }

    public Notebook(Note note) {
        this.notes = new ArrayList<>();
        this.notes.add(note);
    }

    public Notebook(List<Note> note) {
        this.notes = note;
    }

    public List<Note> get_notes(){
        return this.notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void add(String[] arguments){
        if (arguments != null && arguments.length == 2) {
            String title = arguments[0];
            String text = arguments[1];
            var note = new Note(title, text);
            this.notes.add(note);
        } else {
            System.out.println("Wrong number of arguments.");
        }
    }

    public void rm(String[] arguments) {
        if (arguments != null && arguments.length == 1) {
            var new_notes = new ArrayList<>(this.notes);
            var title = arguments[0];
            for (var note : this.notes) {
                if (note.gettitle().equals(title)) {
                    new_notes.remove(note);
                }
            }
            this.notes = new_notes;
        } else {
            System.out.println("Wrong number of arguments.");
        }
    }

    public void show(String[] arguments) {
        if (this.notes == null) {
            System.out.println("Notebook is empty.");
        } else if (arguments!=null && arguments.length == 1) {
            System.out.println("Wrong number of arguments.");
        } else if (arguments!=null && arguments.length >= 2) {
            var res = new Notebook();
            var from = LocalDateTime.parse(arguments[0], DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            var to = LocalDateTime.parse(arguments[1], DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            for (var note : this.notes) {
                if (LocalDateTime.parse(note.getdate(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")).isAfter(from)
                        && LocalDateTime.parse(note.getdate(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")).isBefore(to)) {
                    if (arguments.length >= 3) {
                        boolean containsAll = true;
                        for (int i = 2; i < arguments.length; i++) {
                            if (note.gettitle().contains(arguments[i])) {
                                continue;
                            }
                            containsAll = false;
                            break;
                        }
                        if (containsAll) {
                            res.addNote(note);
                        }
                    } else {
                        res.addNote(note);
                    }
                }
            }
            this.notes = res.get_notes();
        }
    }

    @Override
    public String toString(){
        var str = new StringBuilder();
        str.append("Title\tText\tDate");
        for(var note : this.notes) {
            str.append("\n");
            str.append(note.gettitle());
            str.append("\t");
            str.append(note.gettext());
            str.append("\t");
            str.append(note.getdate());
        }
        return str.toString();
    }
}
