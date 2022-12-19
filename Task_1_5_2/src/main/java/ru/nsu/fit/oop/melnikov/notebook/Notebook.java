package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Notebook {

    @JsonIgnore
    final private static String FILE_PATH = "Task_1_5_2/src/test/resources/notebook.json";

    final private Set<Record> records;

    @JsonCreator
    public Notebook(@JsonProperty("records") Set<Record> records) {
        this.records = records;
    }

    public Notebook() {
        this.records = new HashSet<>();
    }

    public Set<Record> getRecords() {
        return records;
    }

    public static void main(String[] args) {
        if (args.length > 0) {

            ObjectMapper mapper = new ObjectMapper();
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
                case "-show" -> System.out.println(notebook);
            }
        } else System.out.println("usage: notebook -add");
    }

    public void updateFile() {
        ObjectMapper mapper = new ObjectMapper();

        File file = new File(FILE_PATH);
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            mapper.writeValue(file, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeRecord(String name) {
        records.remove(records.stream().filter(record -> record.name().equals(name)).findFirst().orElse(null));
        updateFile();
    }

    public void addRecord(String name, String record) {
        records.add(new Record(name, record));
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