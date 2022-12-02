package ru.nsu.fit.melnikov.oop.recordbook;

/**
 * Represents student card info.
 *
 * @param name          student name
 * @param surname       student surname
 * @param middleName    student middle name
 * @param email         student corporation email
 * @param faculty       student's faculty (department)
 * @param group         student's group
 * @param educationType full time or extramural
 */
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
