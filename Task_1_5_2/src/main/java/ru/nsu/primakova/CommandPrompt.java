package ru.nsu.primakova;

import static ru.nsu.primakova.Json.readJson;
import static ru.nsu.primakova.Json.writeJson;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * CommandPrompt.
 */
public class CommandPrompt {
    private final String filepath;

    @Option(name = "-add", usage = "add note")
    private boolean add;

    @Option(name = "-rm", usage = "remove note")
    private boolean rm;

    @Option(name = "-show", usage = "show notes")
    private boolean show;

    @Option(name = "-help", usage = "show all commands")
    private boolean help;

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
        if (rm) {
            count++;
        }
        if (show) {
            count++;
        }
        if (help) {
            count++;
        }
        if (count > 1) {
            return true;
        }
        return false;
    }

    private void setAllFalse() {
        this.add = false;
        this.rm = false;
        this.show = false;
        this.help = false;
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
            } else if (rm) {
                rmCommand();
            } else if (show) {
                showCommand();
            } else {
                helpCommand();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addCommand() {
        var notes = new Notebook(readJson(filepath));
        notes.add(arguments);
        writeJson(notes.get_notes(), filepath);
    }

    private void rmCommand() {
        var notes = new Notebook(readJson(filepath));
        notes.rm(arguments);
        writeJson(notes.get_notes(), filepath);
    }

    private void showCommand() {
        var notes = new Notebook(readJson(filepath));
        notes.show(arguments);
        System.out.println(notes.toString());
    }

    private void helpCommand() {
        System.out.println("-add: add note to list");
        System.out.println("-rm: remove note from list");
        System.out.println("-show: show notes from list");
    }
}
