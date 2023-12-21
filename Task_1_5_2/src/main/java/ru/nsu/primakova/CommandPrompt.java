package ru.nsu.primakova;

import static ru.nsu.primakova.Json.readJson;
import static ru.nsu.primakova.Json.writeJson;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * CommandPrompt.
 */
public class CommandPrompt {
    private final String filepath;

    @Option(name = "-add", usage = "add note")
    private boolean add;

    @Option(name = "-rm", usage = "remove note")
    private boolean remove;

    @Option(name = "-show", usage = "show notes")
    private boolean show;

    @Argument(metaVar = "arguments", usage = "arguments for command")
    private String[] arguments;

    public CommandPrompt(String filepath) {
        this.filepath = filepath;
    }

    private boolean isWrongNumberOfCommands() {
        int count = 0;
        if (add) {
            count++;
        }
        if (remove) {
            count++;
        }
        if (show) {
            count++;
        }
        if (count > 1) {
            return true;
        }
        return false;
    }

    private void setAllFalse() {
        this.add = false;
        this.remove = false;
        this.show = false;
    }

    public void parse(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        setAllFalse();
        try {
            parser.parseArgument(args);
            if (isWrongNumberOfCommands()) {
                System.out.println("Wrong number of commands.");
            } else if (add) {
                addCommand();
            } else if (remove) {
                rmCommand();
            } else if (show) {
                showCommand();
            } else {
                System.out.println("Wrong name of command.");
            }
        } catch (Exception e) {
            System.out.println("Wrong number of arguments.");
        }
    }

    private void addCommand() {
        if (arguments != null && arguments.length == 2 ) {
            String title = arguments[0];
            String text = arguments[1];
            var note = new Note(title, text);
            var notes = readJson(this.filepath);
            if (notes == null) {
                notes = new ArrayList<>();
            }
            notes.add(note);
            writeJson(notes, this.filepath);
        } else {
            System.out.println("Wrong number of arguments.");
        }
    }

    private void rmCommand() {
        if (arguments != null && arguments.length == 1) {
            var title = arguments[0];
            var notes = readJson(this.filepath);
            if(notes != null) {
                for (var note : notes) {
                    if (note.get_title().equals(title)) {
                        notes.remove(note);
                    }
                }
                writeJson(notes, this.filepath);
            }
        } else {
            System.out.println("Wrong number of arguments.");
        }
    }

    private void showCommand() {
        var notes = readJson(this.filepath);
        if (notes == null) {
            System.out.println("Notebook is empty.");
            return;
        }
        if (arguments == null || arguments.length == 0) {
            for (var note : notes) {
                System.out.println(note);
            }
            return;
        }
        if (arguments.length == 1) {
            System.out.println("Wrong number of arguments.");
        }
        if (arguments.length >= 2) {
            var from = LocalDateTime.parse(arguments[0], DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            var to = LocalDateTime.parse(arguments[1], DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            for (var note : notes) {
                if (LocalDateTime.parse(note.get_date(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")).isAfter(from)
                        && LocalDateTime.parse(note.get_date(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")).isBefore(to)) {
                    if (arguments.length >= 3) {
                        boolean containsAll = true;
                        for (int i = 2; i < arguments.length; i++) {
                            if (note.get_title().contains(arguments[i])) {
                                continue;
                            }
                            containsAll = false;
                            break;
                        }
                        if (containsAll) {
                            System.out.println(note.get_title() + "\t" + note.get_text() + "\t" + note.get_date());
                        }
                    } else {
                        System.out.println(note.get_title() + "\t" + note.get_text() + "\t" + note.get_date());
                    }
                }
            }
        }
    }
}
