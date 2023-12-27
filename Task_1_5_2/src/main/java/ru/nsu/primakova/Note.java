package ru.nsu.primakova;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Note.
 */
public class Note {
    private String text;
    private String title;
    private String date;

    /**
     * Class constructor.
     */
    public Note() {

    }

    /**
     * Class constructor.
     *
     * @param title - title of the new note
     * @param text - title of the new note
     */
    public Note(String title, String text) {
        this.text = text;
        this.title = title;
        var format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm").withZone(ZoneId.systemDefault());
        this.date = LocalDateTime.now().format(format);
    }

    public String gettext() {
        return this.text;
    }

    public String gettitle() {
        return this.title;
    }

    public String getdate() {
        return this.date;
    }

    public void settext(String text) {
        this.text = text;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public void setdate(String date) {
        this.date = date;
    }
}
