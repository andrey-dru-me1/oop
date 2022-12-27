package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    private Notebook notebook;

    @CommandLine.Parameters(index = "0")
    private void setNotebook(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String fullFileName = fileName + ".json";

        try {
            notebook = mapper.readValue(new File(fullFileName), Notebook.class);
        } catch (IOException e) {
            notebook = new Notebook(fullFileName);
        }
    }

    @CommandLine.Option(names = {"-add"}, arity = "2")
    private void addNote(String[] noteInfo) {
        System.out.println("Add record");
        notebook.addRecord(noteInfo[0], noteInfo[1]);
    }

    @CommandLine.Option(names = {"-rm"}, arity = "1")
    private void removeNote(String noteName) {
        System.out.println("Remove record");
        notebook.removeRecord(noteName);
    }

    @CommandLine.Option(names = {"-show"}, arity = "0..")
    private void showAllNotes(String[] data) {
        if (data.length < 2) {
            System.out.println(notebook);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y HH:mm", Locale.ENGLISH).withZone(ZoneId.systemDefault());
            Instant from = LocalDateTime.parse(data[0], formatter).atZone(ZoneId.systemDefault()).toInstant();
            Instant to = LocalDateTime.parse(data[1], formatter).atZone(ZoneId.systemDefault()).toInstant();
            System.out.print(notebook.showWithTimeLimits(formatter, from, to, Arrays.stream(data).skip(2).toArray(String[]::new)));
        }
    }

    public static void main(String @NotNull [] args) {

        CommandLine.populateCommand(new Main(), args);

    }
}
