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
        return "name='" + name + '\'' +
                "\nsurname='" + surname + '\'' +
                "\nmiddleName='" + middleName + '\'' +
                "\nemail='" + email + '\'' +
                "\nfaculty='" + faculty + '\'' +
                "\ngroup=" + group +
                "\neducationType=" + educationType;
    }

    public enum EducationType {
        FULL_TIME,
        EXTRAMURAL
    }
}
