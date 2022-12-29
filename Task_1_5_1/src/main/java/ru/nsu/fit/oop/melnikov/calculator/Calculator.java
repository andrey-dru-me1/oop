package ru.nsu.fit.oop.melnikov.calculator;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.exception.ParseOperationException;
import ru.nsu.fit.oop.melnikov.calculator.exception.WrongOperandsCountException;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Parsable;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;
import ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations.*;

import java.util.*;

/**
 * Implementation of calculator with settable and extendable set of supported operations.
 */
public class Calculator {

    /**
     * Set of supported operations.
     */
    private final List<Parsable> operations;

    public Calculator(List<Parsable> operations) {
        this.operations = operations;
    }

    /**
     * Parses an input expression.
     *
     * @param scanner scanner that have string to parse
     * @return result of input string expression
     * @throws WrongOperandsCountException when arity of operation does not
     *                                     match the count of operands in string
     */
    private Value parseAtom(@NotNull Scanner scanner) {

        String buf;
        try {
            buf = scanner.next();
        } catch (NoSuchElementException exception) {
            throw new WrongOperandsCountException();
        }

        Parsable parsable = null;
        for (Parsable i : operations) {

            parsable = i.parse(buf);

            if (parsable != null) {
                break;
            }

        }

        if (parsable == null) {
            throw new ParseOperationException();
        }

        List<Value> operands = new ArrayList<>();
        for (int i = 0; i < parsable.getArity(); i++) {
            operands.add(parseAtom(scanner));
        }
        if (parsable instanceof Value val && operands.size() == 0) {
            return val;
        }
        if (parsable instanceof Operation op) {
            return op.calculate(operands);
        }
        throw new ParseOperationException();
    }

    /**
     * Loads all basic functions and constants in calculator and execute it.
     *
     * @param args aren't expected and aren't handled in this implementation.
     * @throws WrongOperandsCountException when arity of operation does not
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

        System.out.println(calculator.parseAtom(scanner).getValue());

        scanner.close();

    }

    public String calculate(String expression) {
        Scanner scanner = new Scanner(expression);
        String result = this.parseAtom(scanner).getValue();
        scanner.close();
        return result;

    }

}