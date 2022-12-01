package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.util.Map;

public class RecordBook {

    private final String name;
    private final String surname;
    private final String middleName;
    private final String faculty;
    private final int group;
    private final EducationType educationType;
    private final Date issueDate;
    private Date validUntil;
    private Integer currentSemester;

    @NotNull
    private Map<Integer, Map<String, Integer>> grades;


    public RecordBook(
            String name,
            String surname,
            String middleName,
            String faculty,
            int group,
            EducationType educationType,
            Date issueDate,
            Date validUntil
    ) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.faculty = faculty;
        this.group = group;
        this.educationType = educationType;
        this.issueDate = issueDate;
        this.validUntil = validUntil;
    }

    @JsonCreator
    public RecordBook(
            @JsonProperty("name")
            String name,
            @JsonProperty("surname")
            String surname,
            @JsonProperty("middleName")
            String middleName,
            @JsonProperty("faculty")
            String faculty,
            @JsonProperty("group")
            int group,
            @JsonProperty("educationType")
            @NotNull
            String educationType,
            @JsonProperty("issueDate")
            String issueDate,
            @JsonProperty("validUntil")
            String validUntil
    ) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.faculty = faculty;
        this.group = group;
        if (educationType.equals("FULL_TIME")) {
            this.educationType = EducationType.FULL_TIME;
        } else if (educationType.equals("EXTRAMURAL")) {
            this.educationType = EducationType.EXTRAMURAL;
        } else {
            throw new EnumConstantNotPresentException(EducationType.class, educationType);
        }
        this.issueDate = Date.valueOf(issueDate);
        this.validUntil = Date.valueOf(validUntil);
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

    @Override
    public String toString() {
        return "RecordBook{" +
                "\n\tname='" + name + '\'' +
                ",\n\tsurname='" + surname + '\'' +
                ",\n\tmiddleName='" + middleName + '\'' +
                ",\n\teducationType=" + educationType +
                ",\n\tissueDate=" + issueDate +
                ",\n\tvalidUntil=" + validUntil +
                ",\n\tgrades=" + grades +
                "\n}";
    }

    public String getIssueDate() {
        return issueDate.toString();
    }

    public String getValidUntil() {
        return issueDate.toString();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public Map<Integer, Map<String, Integer>> getGrades() {
        return grades;
    }


    public enum EducationType {
        FULL_TIME,
        EXTRAMURAL
    }
}

