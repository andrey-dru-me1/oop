package ru.nsu.fit.oop.melnikov.calculator.operations;

import java.util.List;

public abstract class Operation {

    abstract public int getArity();

    abstract protected Double calculate(List<Double> operands);

    public Double apply(List<Double> operands) throws WrongOperandsCountException {
        if (operands.size() != getArity()) throw new WrongOperandsCountException();
        return calculate(operands);
    }

    static public class WrongOperandsCountException extends Exception {
    }

}
