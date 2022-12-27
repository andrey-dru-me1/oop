package ru.nsu.fit.oop.melnikov.calculator;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.I;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.Number;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

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
    }

    @Test
    void testComplex() {

        Calculator calculator = new Calculator(List.of(

                // Basic operations
                new Plus(), new Minus(), new Multiply(), new Divide(),

                // Trigonometry operations
                new Sin(), new Cos(), new Deg(),

                //Other operations
                new Log(), new Number(),

                // Power operations
                new Sqrt(), new Sqr(), new Pow(),

                // Constants
                new E(), new Pi(), new I()
        ));

        Assertions.assertEquals("(5.0,3.0)", calculator.calculate("+ 5 * 3 i"));

    }

    @Test
    void testException() {
        Assertions.assertThrowsExactly(Operation.WrongOperandsCountException.class, () -> testTemplate("+ 6", ""));
    }

}