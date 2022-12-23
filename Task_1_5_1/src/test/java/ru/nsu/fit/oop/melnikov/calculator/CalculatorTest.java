package ru.nsu.fit.oop.melnikov.calculator;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

class CalculatorTest {

    void testTemplate(@NotNull String input, String expected) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        DecimalFormatSymbols nf = new DecimalFormatSymbols();
        nf.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.#####", nf);

        Calculator.main(new String[]{});
        Assertions.assertEquals(expected, df.format(Double.parseDouble(outputStream.toString().trim())));
    }

    @Test
    void test() {
        testTemplate("sin + - 1 2 1", "0");
        testTemplate("pow / sqrt - * sqr 3 2 2 8 3", "0.125");
        testTemplate("cos / pi 3", "0.5");
        testTemplate("log pow e 3", "3");
        testTemplate("sin deg 30", "0.5");

        System.setIn(new ByteArrayInputStream("+ 5 * i 3".getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Calculator.main(new String[]{});
        Assertions.assertEquals("5.0 + i ( 3.0 )", outputStream.toString().trim());

    }

    @Test
    void testException() {
        Assertions.assertThrowsExactly(Operation.WrongOperandsCountException.class, () -> testTemplate("+ 6", ""));
    }

}