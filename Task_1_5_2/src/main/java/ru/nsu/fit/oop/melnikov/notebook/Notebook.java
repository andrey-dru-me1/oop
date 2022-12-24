package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Notebook {

    @JsonIgnore
    final private static String FILE_PATH = "notebook.json";

    final private Set<BookRecord> records;

    @JsonCreator
    public Notebook(@JsonProperty("records") Set<BookRecord> records) {
        this.records = records;
    }

    public Notebook() {
        this.records = new HashSet<>();
    }

    @SuppressWarnings("unused") // Jackson uses this method to parse the class to json
    public Set<BookRecord> getRecords() {
        return records;
    }

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

                        String str = "";
                        for (BookRecord i : notebook.records.stream().sorted(Comparator.comparing(r -> r.date().getEpochSecond())).toList()) {
                            if (i.date().isAfter(from) && i.date().isBefore(to)) {
                                str += "[" + formatter.format(i.date()) + "] " + i.name() + ": " + i.record() + "\n";
                            }
                        }
                        System.out.print("Notebook:\n" + str);

                    }
                }
            }
        } else System.out.println("usage: notebook -add");
    }

    public void updateFile() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        File file = new File(FILE_PATH);
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, this);
        } catch (IOException ignored) {
        }
    }

    public void removeRecord(String name) {
        records.remove(records.stream().filter(record -> record.name().equals(name)).findFirst().orElse(null));
        updateFile();
    }

    public void addRecord(String name, String record) {
        records.add(new BookRecord(name, record, Instant.now()));
        updateFile();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String i : records.stream().map(record -> record.toString() + "\n").toList()) {
            str.append(i);
        }
        return "Notebook:\n" + str;
    }
}