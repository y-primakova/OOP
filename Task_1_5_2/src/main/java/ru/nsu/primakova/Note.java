package ru.nsu.primakova;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

//    public boolean isMore(String d1,String d2) throws ParseException {
//        var dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//        var date1 = dateFormat.parse(d1);
//        var date2 = dateFormat.parse(d2);
//
////        String[] date1 = d1.split("");
////        String[] date2 = d2.split("");
////        if (Integer.parseInt(date1[6]) >= Integer.parseInt(date2[6])) {
////            if(Integer.parseInt(date1[6]) == Integer.parseInt(date2[6])) {
////                if (Integer.parseInt(date1[7]) >= Integer.parseInt(date2[7])) {
////                    if(Integer.parseInt(date1[7]) == Integer.parseInt(date2[7])) {
////                        if (Integer.parseInt(date1[8]) >= Integer.parseInt(date2[8])) {
////                            if(Integer.parseInt(date1[8]) == Integer.parseInt(date2[8])) {
////                                if (Integer.parseInt(date1[9]) > Integer.parseInt(date2[9])) {
////                                    return true;
////                                }
////                            } else {
////                                return true;
////                            }
////                        }
////                    } else {
////                        return true;
////                    }
////                }
////            } else {
////                return true;
////            }
////        }
////        return false;
//    }

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
