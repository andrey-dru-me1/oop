package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

class RecordBookTest {

    @Test
    void test() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        RecordBook recordBook = mapper.readValue(Paths.get("src/test/resources/MyRecordBook.json").toFile(), RecordBook.class);
        System.out.println(recordBook);
        mapper.writeValue(Paths.get("src/test/resources/file.json").toFile(), recordBook);

        Student me = recordBook.getStudent();

        Assertions.assertEquals("Андрей", me.name());
        Assertions.assertEquals("Мельников", me.surname());
        Assertions.assertEquals("Петрович", me.middleName());
        Assertions.assertEquals("a.melnikov4@g.nsu.ru", me.email());
        Assertions.assertEquals("ФИТ", me.faculty());
        Assertions.assertEquals(21214, me.group());
        Assertions.assertEquals(Student.EducationType.FULL_TIME, me.educationType());

        Assertions.assertEquals("2021-09-01", recordBook.getStringIssueDate());
        Assertions.assertEquals("2023-08-31", recordBook.getStringValidUntil());

        Assertions.assertEquals(3, recordBook.getCurrentSemester());

        Assertions.assertTrue(recordBook.couldBeRedDiploma());
        Assertions.assertFalse(recordBook.willBeIncreasedScholarship());

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/resources/file.txt"));
        writer.write(recordBook.toString());
        writer.close();

        recordBook.setValidUntil("2024-08-31");
        Assertions.assertEquals("2024-08-31", recordBook.getStringValidUntil());

        Semester toClone = recordBook.getSemester(1);
        Semester semester = new Semester(3, toClone.grades());
        semester.addGrade(
                new Subject(
                        "Новый предмет",
                        List.of("Преподаватель"),
                        Subject.GradeType.CREDIT
                ),
                Semester.Grade.PASSED
        );
        recordBook.addSemester(semester);

        recordBook.setSemesters(recordBook.getSemesters());

        System.out.println(recordBook.calculateAverage());

        recordBook.getSemesters().get(1).grades().entrySet().stream().findFirst().orElseThrow().setValue(Semester.Grade.FAILED);

    }

//    @Test
//    void testLol() throws IOException {
//
//        RecordBook recordBook = new RecordBook(new Student("Андрей", "Мельников", "Петрович", "a.melnikov4@g.nsu.ru", "ФИТ", 21214, Student.EducationType.FULL_TIME),
//                "2022-10-10", "2023-10-10", 1);
//
//        Semester semester = new Semester(1, new HashMap<>());
//        semester.addGrade(
//                new Subject(
//                        "Новый предмет",
//                        List.of("Преподаватель"),
//                        Subject.GradeType.CREDIT
//                ),
//                Semester.Grade.PASSED
//        );
//        recordBook.addSemester(semester);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        ByteArrayOutputStream s = new ByteArrayOutputStream();
//        mapper.writeValue(s, recordBook);
//
//        ByteArrayInputStream in = new ByteArrayInputStream(s.toByteArray());
//
//        System.out.println(s);
//
//        recordBook =  mapper.readValue(in, RecordBook.class);
//
//    }

}