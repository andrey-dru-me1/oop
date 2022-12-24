package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Instant;

@JsonSerialize
public record BookRecord(@JsonProperty("name") String name, @JsonProperty("record") String record,
                         @JsonProperty("date") Instant date) {

    @Override
    public String toString() {
        return name + ": " + record;
    }
}
