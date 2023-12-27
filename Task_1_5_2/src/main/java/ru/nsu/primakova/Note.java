package ru.nsu.primakova;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    private String text;
    private String title;
    private String date;

    public Note(){

    }

    public Note(String title, String text){
        this.text = text;
        this.title = title;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String gettext(){
        return this.text;
    }

    public String gettitle(){
        return this.title;
    }

    public String getdate(){
        return this.date;
    }

    public void settext(String text){
        this.text = text;
    }

    public void settitle(String title){
        this.title = title;
    }

    public void setdate(String date){
        this.date = date;
    }
}
