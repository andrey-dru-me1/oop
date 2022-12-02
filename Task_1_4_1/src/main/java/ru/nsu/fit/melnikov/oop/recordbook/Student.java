package ru.nsu.fit.melnikov.oop.recordbook;

public record Student(
        String name,
        String surname,
        String middleName,
        String email,
        String faculty,
        int group,
        EducationType educationType
) {
    public enum EducationType {
        FULL_TIME,
        EXTRAMURAL
    }
}
