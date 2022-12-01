package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.util.Map;

public class RecordBook {

    private final Student student;
    private final Date issueDate;
    private Date validUntil;
    private Integer currentSemester;

    private Map<Integer, Map<String, Integer>> grades;

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

    public Map<Integer, Map<String, Integer>> getGrades() {
        return grades;
    }

    public void setValidUntil(Date newDate) {
        this.validUntil = newDate;
    }

    public void setValidUntil(String newDate) {
        this.validUntil = Date.valueOf(newDate);
    }

    public Double calculateAverage() {
        return (double) (int)
                (this.grades.values()
                        .stream()
                        .mapToDouble(
                                map -> map.values()
                                        .stream()
                                        .mapToDouble(x -> x)
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

