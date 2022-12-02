package ru.nsu.fit.melnikov.oop.recordbook;

import java.util.List;

public record Subject(String subjectName, List<String> teachers, GradeType gradeType) {

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return subjectName;
    }

    public enum GradeType {
        CREDIT,
        GRADED_TEST,
        EXAM
    }
}
