package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Notebook {

    @JsonIgnore
    private final static String FILE_PATH = "notebook.json";

    private final Set<BookRecord> records;

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

    public String showWithTimeLimits(DateTimeFormatter formatter, Instant from, Instant to, String[] substrings) {

        String str = "";
        for (BookRecord i : this.getRecords().stream().sorted(Comparator.comparing(r -> r.date().getEpochSecond())).toList()) {
            boolean hasSubstring = substrings.length == 0;
            for (String substring : substrings) {
                if (i.name().contains(substring)) {
                    hasSubstring = true;
                    break;
                }
            }
            if (i.date().isAfter(from) && i.date().isBefore(to) && hasSubstring) {
                str += "[" + formatter.format(i.date()) + "] " + i.name() + ": " + i.record() + "\n";
            }
        }

        return "Notebook:\n" + str;
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