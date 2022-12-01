package ru.nsu.fit.melnikov.oop.recordbook;

public record Subject(String name, GradeType gradeType, Grade grade) {
    public enum GradeType {
        CREDIT,
        GRADED_TEST,
        EXAM
    }

    public enum Grade {
        FAILED,
        PASSED,
        SATISFYING,
        GOOD,
        EXCELLENT
    }
}
