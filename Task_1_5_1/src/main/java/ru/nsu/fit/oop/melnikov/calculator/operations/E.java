package ru.nsu.fit.oop.melnikov.calculator.operations;

import java.util.List;

public class E extends Operation {

    final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return Math.E;
    }

}
