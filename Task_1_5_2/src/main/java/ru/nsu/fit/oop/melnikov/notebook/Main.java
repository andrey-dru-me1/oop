package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class Main {
    private final static String FILE_PATH = "notebook.json";

    public static void main(String[] args) {
        if (args.length > 0) {

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            Notebook notebook;

            try {
                notebook = mapper.readValue(new File(FILE_PATH), Notebook.class);
            } catch (IOException e) {
                notebook = new Notebook();
            }

            switch (args[0]) {
                case "-add" -> {
                    System.out.println("Add record");
                    notebook.addRecord(args[1], args[2]);
                }
                case "-rm" -> {
                    System.out.println("Remove record");
                    notebook.removeRecord(args[1]);
                }
                case "-show" -> {
                    if (args.length < 3) {
                        System.out.println(notebook);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y HH:mm", Locale.ENGLISH).withZone(ZoneId.systemDefault());
                        Instant from = LocalDateTime.parse(args[1], formatter).atZone(ZoneId.systemDefault()).toInstant();
                        Instant to = LocalDateTime.parse(args[2], formatter).atZone(ZoneId.systemDefault()).toInstant();
                        System.out.print(notebook.showWithTimeLimits(formatter, from, to, Arrays.stream(args).skip(3).toArray(String[]::new)));

                    }
                }
            }
        } else System.out.println("usage: notebook -add");
    }
}
