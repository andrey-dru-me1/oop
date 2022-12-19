package ru.nsu.fit.oop.melnikov.notebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record Record(@JsonProperty("name") String name, @JsonProperty("record") String record) {

    @Override
    public String toString() {
        return name + ": " + record;
    }
}
