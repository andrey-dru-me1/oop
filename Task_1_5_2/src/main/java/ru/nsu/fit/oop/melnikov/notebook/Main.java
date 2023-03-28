package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

/**
 * Entry class that gets command line arguments as an input, chooses notebook to operate with
 * and appoint all the subcommands.
 */
@Command(
        name = "notebook",
        description = "Gives user to work with a notebook"
)
public class Main {

    private Notebook notebook;

    /**
     * Entry point which begins parsing command line arguments.
     *
     * @param args command line arguments to parse
     */
    public static void main(String @NotNull [] args) {
        Main main = new Main();
        CommandLine commandLine = new CommandLine(main);
        main.modifyCommandLine(commandLine);
        commandLine.execute(args);
    }

    /**
     * Remembers the record to operate with.
     */
    @Option(names = "-n", arity = "0..1", defaultValue = "notebook")
    private void chooseNotebook(String notebookName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String fileName = notebookName + ".json";

        try {
            notebook = mapper.readValue(new File(fileName), Notebook.class);
        } catch (IOException e) {
            notebook = new Notebook(fileName);
        }

    }

    public void modifyCommandLine(CommandLine commandLine) {
        commandLine
                .addSubcommand(new AddRecord())
                .addSubcommand(new RemoveRecord())
                .addSubcommand(new ShowRecords());
    }

    @CommandLine.Command(
            name = "-add",
            description = "Adds new note to a notebook"
    )
    private class AddRecord implements Runnable {

        @CommandLine.Parameters(index = "0")
        private String recordName;

        @CommandLine.Parameters(index = "1")
        private String record;

        @Override
        public void run() {
            System.out.println("Add record");
            notebook.addRecord(recordName, record);
        }

    }

    @CommandLine.Command(
            name = "-rm",
            description = "Removes a note with specific name"
    )
    private class RemoveRecord implements Runnable {

        @CommandLine.Parameters(index = "0")
        private String recordName;

        @Override
        public void run() {
            System.out.println("Remove record");
            notebook.removeRecord(recordName);
        }
    }

    @CommandLine.Command(
            name = "-show",
            description = "Shows list of notes sorted by creating time. " +
                    "Depending on data parameters it can cut showing time interval"
    )
    private class ShowRecords implements Runnable {

        @CommandLine.Parameters(arity = "0..*")
        private String[] data;

        @Override
        public void run() {
            if (data == null || data.length < 2) {
                System.out.println(notebook);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y HH:mm", Locale.ENGLISH).withZone(ZoneId.systemDefault());
                Instant from = LocalDateTime.parse(data[0], formatter).atZone(ZoneId.systemDefault()).toInstant();
                Instant to = LocalDateTime.parse(data[1], formatter).atZone(ZoneId.systemDefault()).toInstant();
                System.out.print(notebook.showWithTimeLimits(formatter, from, to, Arrays.stream(data).skip(2).toArray(String[]::new)));
            }
        }
    }

}
