package ru.nsu.fit.melnikov.oop.recordbook;

import java.util.List;
import java.util.Map;

public class RecordBook {

    private final List<Map<String, Integer>> grades;

    public RecordBook(List<Map<String, Integer>> grades) {
        this.grades = grades;
    }

    public Double getAverage() {
        return (double) (int)
                (this.grades
                        .stream()
                        .mapToDouble(
                                map -> map.values()
                                        .stream()
                                        .mapToDouble(x -> x)
                                        .average()
                                        .orElse(Double.NaN)
                        )
                        .average()
                        .orElse(Double.NaN) * 100) / 100;
    }

    public Boolean couldBeRedDiploma() {
        return this.getAverage() == 5.0;
    }

}