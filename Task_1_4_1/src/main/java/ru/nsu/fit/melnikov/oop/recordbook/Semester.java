package ru.nsu.fit.melnikov.oop.recordbook;

import java.util.Set;

public record Semester(int number, Set<Pair<Subject, Grade>> grades) {
    public enum Grade {
        FAILED,
        PASSED,
        SATISFYING,
        GOOD,
        EXCELLENT
    }
}
