package ru.nsu.primakova;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    private final String text;
    private final String title;
    private final String date;

    public Note(String title, String text){
        this.text = text;
        this.title = title;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String get_text(){
        return this.text;
    }

    public String get_title(){
        return this.title;
    }

    public String get_date(){
        return this.date;
    }
}
