package ru.nsu.fit.oop.melnikov.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;

class CalculatorTest {

    void testTemplate(String input, String expected) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        DecimalFormat df = new DecimalFormat("#.#####");

        Calculator.main(new String[]{});
        Assertions.assertEquals(expected, df.format(Double.parseDouble(outputStream.toString().trim())));
    }

    @Test
    void test() {
        testTemplate("sin + - 1 2 1", "0");
        testTemplate("pow / sqrt - * sqr 3 2 2 8 3", "0,125");
        testTemplate("cos / pi 3", "0,5");
        testTemplate("log pow e 3", "3");
    }

}