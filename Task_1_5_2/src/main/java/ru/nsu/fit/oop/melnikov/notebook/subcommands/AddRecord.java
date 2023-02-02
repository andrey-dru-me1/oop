package ru.nsu.fit.oop.melnikov.notebook.subcommands;

import picocli.CommandLine;
import ru.nsu.fit.oop.melnikov.notebook.Notebook;

@CommandLine.Command(
        name = "-add",
        description = "Adds new note to a notebook"
)
public class AddRecord implements Runnable {
    private final Notebook notebook;

    @CommandLine.Parameters(index = "0")
    private String recordName;

    @CommandLine.Parameters(index = "1")
    private String record;

    public AddRecord(Notebook notebook) {
        this.notebook = notebook;
    }

    @Override
    public void run() {
        System.out.println("Add record");
        notebook.addRecord(recordName, record);
    }

}
