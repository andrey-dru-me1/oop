package ru.nsu.fit.oop.melnikov.calculator.operations;

import java.util.List;

public class Cos extends Operation {

    final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return Math.cos(operands.get(0));
    }

}
