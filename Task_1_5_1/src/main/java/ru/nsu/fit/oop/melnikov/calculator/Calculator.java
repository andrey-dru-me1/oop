package ru.nsu.fit.oop.melnikov.calculator;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Number;
import ru.nsu.fit.oop.melnikov.calculator.operations.*;

import java.util.*;

/**
 * Implementation of calculator with settable and extendable set of supported operations.
 */
public class Calculator {

    /**
     * Set of supported operations.
     */
    private final List<Operation> operations;

    public Calculator(List<Operation> operations) {
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

        Operation operation = null;
        for (Operation i : operations) {

            operation = i.parse(buf);

            if (operation != null) {
                break;
            }

        }

        if (operation == null) {
            throw new Operation.ParseOperationException();
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

        Calculator calculator = new Calculator(Arrays.asList(

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