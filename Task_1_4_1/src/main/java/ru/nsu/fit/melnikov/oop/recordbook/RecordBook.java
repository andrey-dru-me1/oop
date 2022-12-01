package ru.nsu.fit.melnikov.oop.recordbook;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class RecordBook {

    private final String name;
    private final String surname;
    private final String middleName;
    private final EducationType educationType;
    private final Date issueDate;
    private Date validUntil;

    private List<Map<String, Integer>> grades;

    public RecordBook(
            String name,
            String surname,
            String middleName,
            EducationType educationType,
            Date issueDate,
            Date validUntil
    ) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.educationType = educationType;
        this.issueDate = issueDate;
        this.validUntil = validUntil;
    }

    public RecordBook(
            String name,
            String surname,
            String middleName,
            @NotNull String educationType,
            String issueDate,
            String validUntil
    ) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
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

    public Double getAverage() {
        return (double) (int)
                (this.grades
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
        return this.getAverage() > 5.0 * 0.75;
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
}

enum EducationType {
    FULL_TIME,
    EXTRAMURAL
}