package ru.nsu.fit.melnikov.oop.recordbook;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RecordBookTest {

    @Test
    void test() {

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
                "FULL_TIME",
                "2021-09-01",
                "2023-08-31"
        );
        System.out.println(recordBook);

//        RecordBook recordBook = new RecordBook(grades);
//        Assertions.assertEquals(4.66, recordBook.getAverage());
//        Assertions.assertTrue(recordBook.couldBeRedDiploma());
    }

}