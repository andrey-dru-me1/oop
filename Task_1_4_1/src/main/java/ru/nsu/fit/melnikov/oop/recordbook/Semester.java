package ru.nsu.fit.melnikov.oop.recordbook;

import java.util.Set;

public record Semester(int number, Set<Pair<Subject, Grade>> grades) {

    public void addGrade(Subject subject, Grade grade) {
        grades.add(new Pair<>(subject, grade));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public enum Grade {
        FAILED,
        PASSED,
        SATISFYING,
        GOOD,
        EXCELLENT
    }
}
