package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.List;

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

    public void addSemester(Semester semester) {
        semesters.add(semester.number() - 1, semester);
    }

    public void updateCurrentSemester() {
        this.setCurrentSemester(this.getCurrentSemester() + 1);
    }

    public Double calculateAverage() {
        return (double) (int)
                (this.semesters
                        .stream()
                        .mapToDouble(
                                map -> map.grades()
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

    public Boolean couldBeRedDiploma() {
        return this.calculateAverage() > 5.0 * 0.75;
    }

}

