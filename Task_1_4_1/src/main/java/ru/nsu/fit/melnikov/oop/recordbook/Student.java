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
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", faculty='" + faculty + '\'' +
                ", group=" + group +
                ", educationType=" + educationType +
                '}';
    }

    public enum EducationType {
        FULL_TIME,
        EXTRAMURAL
    }
}
