package ru.nsu.fit.oop.melnikov.calculator;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.*;

import java.util.*;

public class Calculator {

    private final Map<String, Operation> operations;

    public Calculator(Map<String, Operation> operations) {
        this.operations = operations;
    }

    private Double parseAtom(@NotNull Scanner scanner) throws Operation.WrongOperandsCountException {

        String buf;
        try {
            buf = scanner.next();
        } catch (NoSuchElementException exception) {
            throw new Operation.WrongOperandsCountException();
        }

        Operation operation = operations.get(buf);

        if (operation == null) {
            return Double.parseDouble(buf);
        }

        List<Double> operands = new ArrayList<>();
        for (int i = 0; i < operation.getArity(); i++) {
            operands.add(parseAtom(scanner));
        }
        return operation.apply(operands);
    }

    public static void main(String[] args) throws Operation.WrongOperandsCountException {
        Calculator calculator = new Calculator(Map.ofEntries(

                // Basic operations

                Map.entry("+", new Plus()),
                Map.entry("-", new Minus()),
                Map.entry("*", new Multiply()),
                Map.entry("/", new Divide()),

                // Trigonometry operations

                Map.entry("sin", new Sin()),
                Map.entry("cos", new Cos()),

                //Other operations

                Map.entry("log", new Log()),

                // Power operations

                Map.entry("sqrt", new Sqrt()),
                Map.entry("sqr", new Sqr()),
                Map.entry("pow", new Pow()),

                // Constants

                Map.entry("e", new E()),
                Map.entry("pi", new Pi())
        ));

        Scanner scanner = new Scanner(System.in);

        Double result = calculator.parseAtom(scanner);
        System.out.println(result);

        scanner.close();

    }

}