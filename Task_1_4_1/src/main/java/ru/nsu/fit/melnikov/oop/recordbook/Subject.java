package ru.nsu.fit.melnikov.oop.recordbook;

import java.util.List;

/**
 * Represents subject with its name, list of teachers and type of grade.
 *
 * @param subjectName name of subject
 * @param teachers    list of teachers
 * @param gradeType   credit, graded test or exam
 */
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
