package ru.nsu.primakova;

/**
 * Main.
 */
public class Main {
    /**
     * main.
     */
    public static void main(String[] args) {
        String filepath = "notebook.json";
        CommandPrompt commandParser = new CommandPrompt(filepath);
        String[] command1 = new String[]{"-add", "title1", "text1"};
        commandParser.parse(command1);
//        var notes = read(filepath);
//        for(var note:notes){
//            System.out.println(note.get_title() + "\t" + note.get_text() + "\t" + note.get_date());
//        }
    }
}