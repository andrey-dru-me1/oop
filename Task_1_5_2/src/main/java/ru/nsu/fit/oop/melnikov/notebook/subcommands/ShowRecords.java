package ru.nsu.fit.oop.melnikov.notebook.subcommands;

import picocli.CommandLine;
import ru.nsu.fit.oop.melnikov.notebook.Notebook;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

@CommandLine.Command(name = "-show")
public class ShowRecords implements Runnable {

    private final Notebook notebook;

    @CommandLine.Parameters(arity = "0..*")
    private String[] data;

    public ShowRecords(Notebook notebook) {
        this.notebook = notebook;
    }

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
