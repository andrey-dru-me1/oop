package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class Main {

    public static void main(String @NotNull [] args) {
        if (args.length > 1) {

            final String FILE_PATH = args[0] + ".json";

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            Notebook notebook;

            try {
                notebook = mapper.readValue(new File(FILE_PATH), Notebook.class);
            } catch (IOException e) {
                notebook = new Notebook(FILE_PATH);
            }

            switch (args[1]) {
                case "-add" -> {
                    System.out.println("Add record");
                    notebook.addRecord(args[2], args[3]);
                }
                case "-rm" -> {
                    System.out.println("Remove record");
                    notebook.removeRecord(args[2]);
                }
                case "-show" -> {
                    if (args.length < 4) {
                        System.out.println(notebook);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y HH:mm", Locale.ENGLISH).withZone(ZoneId.systemDefault());
                        Instant from = LocalDateTime.parse(args[2], formatter).atZone(ZoneId.systemDefault()).toInstant();
                        Instant to = LocalDateTime.parse(args[3], formatter).atZone(ZoneId.systemDefault()).toInstant();
                        System.out.print(notebook.showWithTimeLimits(formatter, from, to, Arrays.stream(args).skip(4).toArray(String[]::new)));

                    }
                }
            }
        } else
            System.out.println("usage: notebook [parameter]\n\tparameters:\n\t\t-add\n\t\t--rm\n\t\t-show");
    }
}
