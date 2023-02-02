package ru.nsu.fit.oop.melnikov.notebook.subcommands;

import picocli.CommandLine;
import ru.nsu.fit.oop.melnikov.notebook.Notebook;

@CommandLine.Command(
        name = "-rm",
        description = "Removes a note with specific name"
)
public class RemoveRecord implements Runnable {
    private final Notebook notebook;

    @CommandLine.Parameters(index = "0")
    private String recordName;

    public RemoveRecord(Notebook notebook) {
        this.notebook = notebook;
    }

    @Override
    public void run() {
        System.out.println("Remove record");
        notebook.removeRecord(recordName);
    }

}
