package ru.nsu.fit.oop.melnikov.calculator;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations.*;

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
    private Object parseAtom(@NotNull Scanner scanner) {

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

        List<Object> operands = new ArrayList<>();
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
                new DoublePlus(), new DoubleMinus(), new DoubleMultiply(), new DoubleDivide(),

                // Trigonometry operations
                new DoubleSin(), new DoubleCos(), new DoubleDeg(),

                //Other operations
                new DoubleLog(), new DoubleNumber(),

                // Power operations
                new DoubleSqrt(), new DoubleSqr(), new DoublePow(),

                // Constants
                new DoubleE(), new DoublePi()
        ));

        Scanner scanner = new Scanner(System.in);

        System.out.println(calculator.parseAtom(scanner));

        scanner.close();

    }

    public String calculate(String expression) {
        Scanner scanner = new Scanner(expression);
        String result = this.parseAtom(scanner).toString();
        scanner.close();
        return result;

    }

}