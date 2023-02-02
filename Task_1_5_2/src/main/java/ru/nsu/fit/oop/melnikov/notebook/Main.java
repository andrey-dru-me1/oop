package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import ru.nsu.fit.oop.melnikov.notebook.subcommands.AddRecord;
import ru.nsu.fit.oop.melnikov.notebook.subcommands.RemoveRecord;
import ru.nsu.fit.oop.melnikov.notebook.subcommands.ShowRecords;

import java.io.File;
import java.io.IOException;

@Command(
        name = "notebook",
        description = "Gives user to work with a notebook"
)
public class Main {

    private CommandLine commandLine;

    public static void main(String @NotNull [] args) {
        Main main = new Main();
        CommandLine commandLine = new CommandLine(main);
        main.setCommandLine(commandLine);
        commandLine.execute(args);
    }

    public void setCommandLine(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    @Parameters(index = "0", arity = "0")
    private void chooseNotebook(String noteName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String fileName = noteName + ".json";

        Notebook notebook;
        try {
            notebook = mapper.readValue(new File(fileName), Notebook.class);
        } catch (IOException e) {
            notebook = new Notebook(fileName);
        }

        commandLine
                .addSubcommand(new AddRecord(notebook))
                .addSubcommand(new RemoveRecord(notebook))
                .addSubcommand(new ShowRecords(notebook));
    }

}
