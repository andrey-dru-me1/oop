package ru.nsu.fit.melnikov.oop.recordbook;

import java.util.List;

public record Subject(String subjectName, List<String> teachers, GradeType gradeType) {
    public enum GradeType {
        CREDIT,
        GRADED_TEST,
        EXAM
    }
}
