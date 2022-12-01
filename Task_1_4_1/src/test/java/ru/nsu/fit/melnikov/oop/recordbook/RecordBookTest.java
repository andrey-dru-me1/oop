package ru.nsu.fit.melnikov.oop.recordbook;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RecordBookTest {

    @Test
    void test() throws IOException {

        List<Map<String, Integer>> grades = new ArrayList<>();
        Map<String, Integer> firstSemester = new HashMap<>();
        firstSemester.put("Цифровые платформы", 5);
        firstSemester.put("Введение в алгебру и анализ", 4);
        firstSemester.put("Декларативное программирование", 5);
        firstSemester.put("Императивное программрование", 5);
        firstSemester.put("История", 5);
        firstSemester.put("Основы культуры речи", 5);
        Map<String, Integer> secondSemester = new HashMap<>();
        secondSemester.put("Введение в алгебру и анализ", 4);
        secondSemester.put("Введение в дискретную математику и математическую логику", 5);
        secondSemester.put("Декларативное программирование", 5);
        secondSemester.put("Императивное программирование", 5);
        secondSemester.put("Иностранный язык", 4);
        secondSemester.put("Цифровые платформы", 4);
        grades.add(firstSemester);
        grades.add(secondSemester);

        RecordBook recordBook = new RecordBook(
                "Андрей",
                "Мельников",
                "Петрович",
                "ФИТ",
                21214,
                "FULL_TIME",
                "2021-09-01",
                "2023-08-31"
        );

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.writeValue(Paths.get("src/test/resources/file.txt").toFile(), recordBook);

        recordBook = mapper.readValue(Paths.get("src/test/resources/MyRecordBook.txt").toFile(), RecordBook.class);
        System.out.println(recordBook);
        mapper.writeValue(Paths.get("src/test/resources/file2.txt").toFile(), recordBook);
    }

}