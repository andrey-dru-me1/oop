package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RecordBookTest {

    @Test
    void test() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        RecordBook recordBook = mapper.readValue(Paths.get("src/test/resources/MyRecordBook.json").toFile(), RecordBook.class);
        System.out.println(recordBook);
        mapper.writeValue(Paths.get("src/test/resources/file.json").toFile(), recordBook);

        recordBook = new RecordBook(
                new Student
                        (
                                "Андрей",
                                "Мельников",
                                "Петрович",
                                "a.melnikov4@g.nsu.ru",
                                "ФИТ",
                                21214,
                                Student.EducationType.FULL_TIME
                        ),
                "2021-09-01",
                "2023-08-31");
        Set<Pair<Subject, Semester.Grade>> grades = new HashSet<>();
        grades.add(new Pair<>(new Subject("Дифффуры", List.of("Шваб"), Subject.GradeType.EXAM), Semester.Grade.GOOD));
        List<Semester> semesters = List.of(new Semester(0, grades));
        recordBook.setSemesters(semesters);
        mapper.writeValue(Paths.get("src/test/resources/file2.txt").toFile(), recordBook);
        recordBook = mapper.readValue(Paths.get("src/test/resources/file2.txt").toFile(), RecordBook.class);
        System.out.println(recordBook);
    }

}