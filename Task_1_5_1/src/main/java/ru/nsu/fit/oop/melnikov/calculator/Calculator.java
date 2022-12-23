package ru.nsu.fit.oop.melnikov.calculator;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.*;

import java.util.*;

/**
 * Implementation of calculator with settable and extendable set of supported operations.
 */
public class Calculator {

    /**
     * Set of supported operations.
     */
    private final Map<String, Operation> operations;

    public Calculator(Map<String, Operation> operations) {
        this.operations = operations;
    }

    /**
     * Parses an input expression.
     *
     * @param scanner scanner that have string to parse
     * @return result of input string expression
     * @throws Operation.WrongOperandsCountException when arity of operation does not
     *                                               match the count of operands in string
     */
    private Complex parseAtom(@NotNull Scanner scanner) {

        String buf;
        try {
            buf = scanner.next();
        } catch (NoSuchElementException exception) {
            throw new Operation.WrongOperandsCountException();
        }

        Operation operation = operations.get(buf);

        if (operation == null) {
            return Complex.ofCartesian(Double.parseDouble(buf), 0);
        }

        List<Complex> operands = new ArrayList<>();
        for (int i = 0; i < operation.getArity(); i++) {
            operands.add(parseAtom(scanner));
        }
        return operation.apply(operands);
    }

    /**
     * Loads all basic functions and constants in calculator and execute it.
     *
     * @param args aren't expected and aren't handled in this implementation.
     * @throws Operation.WrongOperandsCountException when arity of operation does not
     *                                               match the count of operands in string
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator(Map.ofEntries(

                // Basic operations

                Map.entry("+", new Plus()),
                Map.entry("-", new Minus()),
                Map.entry("*", new Multiply()),
                Map.entry("/", new Divide()),

                // Trigonometry operations

                Map.entry("sin", new Sin()),
                Map.entry("cos", new Cos()),
                Map.entry("deg", new Deg()),

                //Other operations

                Map.entry("log", new Log()),

                // Power operations

                Map.entry("sqrt", new Sqrt()),
                Map.entry("sqr", new Sqr()),
                Map.entry("pow", new Pow()),

                // Constants

                Map.entry("e", new E()),
                Map.entry("pi", new Pi()),
                Map.entry("i", new I())
        ));

        Scanner scanner = new Scanner(System.in);

        Complex result = calculator.parseAtom(scanner);

        if (result.getImaginary() != 0 && result.getReal() != 0) {
            System.out.println(result.getReal() + " + i ( " + result.getImaginary() + " )");
        } else if (result.getImaginary() != 0) {
            System.out.println("i ( " + result.getImaginary() + " )");
        } else {
            System.out.println(result.getReal());
        }

        scanner.close();

    }

}