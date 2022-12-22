package ru.nsu.fit.oop.melnikov.calculator.operations;

import java.util.List;

public class Multiply extends Operation {

    final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return operands.get(0) * operands.get(1);
    }

}
