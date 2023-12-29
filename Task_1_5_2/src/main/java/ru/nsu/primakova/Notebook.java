package ru.nsu.primakova;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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

    public List<Note> get_notes() {
        return this.notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    /**
     * add note.
     */
    public void add(String[] arguments) {
        if (arguments != null && arguments.length == 2) {
            String title = arguments[0];
            String text = arguments[1];
            var note = new Note(title, text);
            this.notes.add(note);
        } else {
            System.out.println("Wrong number of arguments.");
        }
    }

    /**
     * remove note.
     */
    public void rm(String[] arguments) {
        if (arguments != null && arguments.length == 1) {
            var res = new ArrayList<>(this.notes);
            var title = arguments[0];
            for (var note : this.notes) {
                if (note.gettitle().equals(title)) {
                    res.remove(note);
                }
            }
            this.notes = res;
        } else {
            System.out.println("Wrong number of arguments.");
        }
    }

    /**
     * show notes.
     */
    public void show(String[] arguments) {
        if (this.notes == null) {
            System.out.println("Notebook is empty.");
        } else if (arguments != null && arguments.length >= 2) {
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
        sortDate();
    }

    private void sortDate() {
        for (var note1 : this.notes) {
            for (var note2 : this.notes) {
                var date2 = LocalDateTime.parse(note2.getdate(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                if (LocalDateTime.parse(note1.getdate(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")).isBefore(date2)) {
                    var temp = new Note(note1.gettitle(), note1.gettext());
                    temp.setdate(note1.getdate());
                    note1.settext(note2.gettext());
                    note1.settitle(note2.gettitle());
                    note1.setdate(note2.getdate());
                    note2.settext(temp.gettext());
                    note2.settitle(temp.gettitle());
                    note2.setdate(temp.getdate());
                }
            }
        }
    }

    @Override
    public String toString() {
        var str = new StringBuilder();
        str.append("Title\tText\tDate");
        for (var note : this.notes) {
            str.append("\n");
            str.append(note.gettitle());
            str.append("\t");
            str.append(note.gettext());
            str.append("\t");
            var format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").withZone(ZoneOffset.UTC);
            var date = ZonedDateTime.parse(note.getdate(), format).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().format(format);
            str.append(date);
        }
        return str.toString();
    }
}
