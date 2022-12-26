package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of student's record book
 */
public class RecordBook {

    private final Student student;
    private final Date issueDate;
    private Date validUntil;
    private Integer currentSemester;

    private List<Semester> semesters;

    @JsonCreator
    public RecordBook(
            @JsonProperty("student")
            Student student,
            @JsonProperty("issueDate")
            String issueDate,
            @JsonProperty("validUntil")
            String validUntil,
            @JsonProperty("currentSemester")
            int currentSemester) {
        this.student = student;
        this.issueDate = Date.valueOf(issueDate);
        this.validUntil = Date.valueOf(validUntil);
        this.currentSemester = currentSemester;
        this.semesters = new ArrayList<>();
    }

    public Student getStudent() {
        return student;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    @JsonGetter("issueDate")
    public String getStringIssueDate() {
        return this.getIssueDate().toString();
    }

    public Date getValidUntil() {
        return validUntil;
    }

    @JsonGetter("validUntil")
    public String getStringValidUntil() {
        return this.getValidUntil().toString();
    }

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public Semester getSemester(int number) {
        return this.semesters.get(number - 1);
    }

    public void setValidUntil(Date newDate) {
        this.validUntil = newDate;
    }

    public void setValidUntil(String newDate) {
        this.setValidUntil(Date.valueOf(newDate));
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public void setCurrentSemester(int number) {
        this.currentSemester = number;
    }

    /**
     * Adds new semester.
     *
     * @param semester new semester to add
     */
    public void addSemester(Semester semester) {
        semesters.add(semester.number() - 1, semester);
        this.updateCurrentSemester();
    }

    /**
     * Updates current semester number to the next one.
     */
    public void updateCurrentSemester() {
        this.setCurrentSemester(this.getCurrentSemester() + 1);
    }

    /**
     * Calculates average mark from all the marks in record book.
     *
     * @return average mark ever
     */
    public Double calculateAverage() {
        return (double) (int)
                (this.semesters
                        .stream()
                        .mapToDouble(
                                map -> map.grades()
                                        .entrySet()
                                        .stream()
                                        .filter(x -> !x.getKey().gradeType().equals(Subject.GradeType.CREDIT))
                                        .mapToDouble(x -> {
                                            Semester.Grade grade = x.getValue();
                                            return switch (grade) {
                                                case SATISFYING -> 3.0;
                                                case GOOD -> 4.0;
                                                case EXCELLENT -> 5.0;
                                                default ->
                                                        throw new EnumConstantNotPresentException(Semester.Grade.class, grade.name());
                                            };
                                        })
                                        .average()
                                        .orElse(Double.NaN)
                        )
                        .average()
                        .orElse(Double.NaN) * 100) / 100;
    }

    /**
     * Checks if student will receive red diploma.
     *
     * @return true if student can receive red diploma.
     */
    public Boolean couldBeRedDiploma() {
        return this.calculateAverage() > 5.0 * 0.75;
    }

    /**
     * Checks if student could have increased stipend according to the last semester.
     *
     * @return true if a stipend should be increased according to the last semester
     */
    public Boolean willBeIncreasedScholarship() {
        if (currentSemester <= 1) return false;
        for (
                Semester.Grade i :
                this.getSemester(this.getCurrentSemester() - 1).grades()
                        .entrySet()
                        .stream()
                        .filter(x -> !x.getKey().gradeType().equals(Subject.GradeType.CREDIT))
                        .map(java.util.Map.Entry::getValue)
                        .toList()
        ) {
            if (i != Semester.Grade.EXCELLENT) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String sharps = "\n\n############################\n\n";
        return sharps +
                student +
                "\nissueDate=" + issueDate +
                "\nvalidUntil=" + validUntil +
                sharps + semesters + sharps;
    }
}

