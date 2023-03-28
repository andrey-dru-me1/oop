package ru.nsu.fit.oop.melnikov.calculator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.calculator.exception.WrongOperandsCountException;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexCos;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexDeg;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexDivide;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexE;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexLog;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexMinus;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexMultiply;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexNumber;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexPi;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexPlus;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexPow;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexSin;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexSqr;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.ComplexSqrt;
import ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations.I;

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
    Assertions.assertEquals(expected,
        df.format(Double.parseDouble(outputStream.toString().trim())));
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
        new ComplexPlus(), new ComplexMinus(), new ComplexMultiply(), new ComplexDivide(),

        // Trigonometry operations
        new ComplexSin(), new ComplexCos(), new ComplexDeg(),

        //Other operations
        new ComplexLog(), new ComplexNumber(),

        // Power operations
        new ComplexSqrt(), new ComplexSqr(), new ComplexPow(),

        // Constants
        new ComplexE(), new ComplexPi(), new I()
    ));

    Assertions.assertEquals("(5.0,3.0)", calculator.calculate("+ 5 * 3 i"));
    Assertions.assertEquals("(0.0,0.0)", calculator.calculate("sin + - 1 2 1"));
//        Assertions.assertEquals("(0.125,0.0)", calculator.calculate("pow / sqrt - * sqr 3 2 2 8 3"));
//        Assertions.assertEquals("(0.5,0.0)", calculator.calculate("cos / pi 3"));
    Assertions.assertEquals("(3.0,0.0)", calculator.calculate("log pow e 3"));
//        Assertions.assertEquals("(0.5,0.0)", calculator.calculate("sin deg 30"));

  }

  @Test
  void testException() {
    Assertions.assertThrowsExactly(WrongOperandsCountException.class,
        () -> testTemplate("+ 6", ""));
  }

}