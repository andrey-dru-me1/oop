package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;
import java.util.Set;

/**
 * Represents one semester with all its subjects and grades.
 *
 * @param number number of the semester
 * @param grades map of subjects and grades
 */
public record Semester(int number,
                       @JsonSerialize(using = CustomMapSerializer.class)
                       @JsonDeserialize(using = CustomMapDeserializer.class)
                       Map<Subject, Grade> grades) {

    /**
     * Adds new grade for the semester.
     *
     * @param subject new subject to add grade to
     * @param grade   grade to bind with subject
     */
    public void addGrade(Subject subject, Grade grade) {

        Subject.GradeType gradeType = subject.gradeType();

        if (!grade.checkCorrectness(gradeType)) {
            throw new IllegalArgumentException();
        }

        grades.put(subject, grade);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        String result = "\n----------------------\n" + number + "\n\n";
        for (Map.Entry<Subject, Grade> grade : grades.entrySet()) {
            result += grade.getKey() + ": " + grade.getValue() + "\n";
        }
        return result;
    }

    public enum Grade {
        FAILED(Set.of(Subject.GradeType.CREDIT, Subject.GradeType.GRADED_TEST, Subject.GradeType.EXAM)),
        PASSED(Set.of(Subject.GradeType.CREDIT)),
        SATISFYING(Set.of(Subject.GradeType.GRADED_TEST, Subject.GradeType.EXAM)),
        GOOD(Set.of(Subject.GradeType.GRADED_TEST, Subject.GradeType.EXAM)),
        EXCELLENT(Set.of(Subject.GradeType.GRADED_TEST, Subject.GradeType.EXAM));

        private final Set<Subject.GradeType> appropriateTypes;

        Grade(Set<Subject.GradeType> types) {
            appropriateTypes = types;
        }

        public boolean checkCorrectness(Subject.GradeType gradeType) {
            return appropriateTypes.contains(gradeType);
        }

    }
}
