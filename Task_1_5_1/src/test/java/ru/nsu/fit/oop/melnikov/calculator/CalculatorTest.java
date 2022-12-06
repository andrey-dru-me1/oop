package ru.nsu.fit.oop.melnikov.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

class CalculatorTest {

    @Test
    void test() {
        String params = "sin + - 1 2 1";
        System.setIn(new ByteArrayInputStream(params.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Calculator.main(new String[]{});
        Assertions.assertEquals("0.0", outputStream.toString().trim());
    }

    @Test
    void testMore() {
        String params = "pow / sqrt - * sqr 3 2 2 8 3";
        System.setIn(new ByteArrayInputStream(params.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Calculator.main(new String[]{});
        Assertions.assertEquals("0.125", outputStream.toString().trim());
    }
}