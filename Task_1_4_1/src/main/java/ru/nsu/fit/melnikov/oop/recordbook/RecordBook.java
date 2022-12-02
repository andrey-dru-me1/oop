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

    public RecordBook(Student student, Date issueDate, Date validUntil) {
        this.student = student;
        this.issueDate = issueDate;
        this.validUntil = validUntil;
    }

    @JsonCreator
    public RecordBook(
            @JsonProperty("student")
            Student student,
            @JsonProperty("issueDate")
            String issueDate,
            @JsonProperty("validUntil")
            String validUntil) {
        this.student = student;
        this.issueDate = Date.valueOf(issueDate);
        this.validUntil = Date.valueOf(validUntil);
    }

    public RecordBook(
            String name,
            String surname,
            String middleName,
            String email,
            String faculty,
            int group,
            Student.EducationType educationType,
            Date issueDate,
            Date validUntil
    ) {
        this.student = new Student(name, surname, middleName, email, faculty, group, educationType);
        this.issueDate = issueDate;
        this.validUntil = validUntil;
    }

    public Student getStudent() {
        return student;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    @JsonGetter("issueDate")
    public String getStringIssueDate() {
        return issueDate.toString();
    }

    public Date getValidUntil() {
        return validUntil;
    }

    @JsonGetter("validUntil")
    public String getStringValidUntil() {
        return validUntil.toString();
    }

    public Integer getCurrentSemester() {
        return currentSemester;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setValidUntil(Date newDate) {
        this.validUntil = newDate;
    }

    public void setValidUntil(String newDate) {
        this.validUntil = Date.valueOf(newDate);
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
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

