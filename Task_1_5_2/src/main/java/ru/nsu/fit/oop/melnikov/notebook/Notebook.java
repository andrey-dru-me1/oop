package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.annotation.JsonCreator;
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

/**
 * Represents a notebook with list of notes (records).
 */
public class Notebook {

    /**
     * Name of .json-file where notebook is stored.
     */
    private final String fileName;

    /**
     * Set of records in notebook.
     */
    private final Set<BookRecord> records;

    /**
     * Class constructor which is used to load all the data from .json-file.
     *
     * @param filename name of .json-file where notebook is stored
     * @param records  set of records in notebook
     */
    @JsonCreator
    public Notebook(@JsonProperty("filename") String filename, @JsonProperty("records") Set<BookRecord> records) {
        this.fileName = filename;
        this.records = records;
    }

    /**
     * Creates new empty notebook with specific filename.
     *
     * @param filename name of .json-file where all the data will be stored
     */
    public Notebook(String filename) {
        this.fileName = filename;
        this.records = new HashSet<>();
    }

    /**
     * Returns a set of records in notebook.
     *
     * @return set of records in notebook
     */
    @SuppressWarnings("unused") // Jackson uses this method to parse the class to json
    public Set<BookRecord> getRecords() {
        return records;
    }

    /**
     * Returns a json-filename.
     *
     * @return json-filename
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Write all the data from current object to a json-file.
     */
    public void updateFile() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        File file = new File(fileName);
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, this);
        } catch (IOException ignored) {
        }
    }

    /**
     * Shows all the records from a notebook sorted by date and filtered by date interval and
     * record header words.
     *
     * @param formatter  formatter of date time
     * @param from       date and time when queried time interval for record filter is beginning
     * @param to         date and time when queried time interval for record filter is ending
     * @param substrings array of substrings which should be in record header
     * @return one string with a whole notebook sorted by the creating date
     * and filtered by time interval and record header words
     */
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

        return "Notebook ( " + this.fileName + " ) :\n" + str;
    }

    /**
     * Removes record with specific name.
     *
     * @param name name of record to remove
     */
    public void removeRecord(String name) {
        records.remove(records.stream().filter(record -> record.name().equals(name)).findFirst().orElse(null));
        updateFile();
    }

    /**
     * Adds new record to the notebook.
     *
     * @param name   name of new record to write
     * @param record text that must be in a record
     */
    public void addRecord(String name, String record) {
        records.add(new BookRecord(name, record, Instant.now()));
        updateFile();
    }

    /**
     * Returns a string containing all the records from a notebook sorted by time.
     *
     * @return string containing all the records from a notebook sorted by time
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (String i :
                this
                        .getRecords()
                        .stream()
                        .sorted(Comparator.comparing(r -> r.date().getEpochSecond()))
                        .map(record -> record + "\n")
                        .toList()
        ) {
            str.append(i);
        }
        return "Notebook ( " + this.fileName + " ) :\n"  + str;
    }
}