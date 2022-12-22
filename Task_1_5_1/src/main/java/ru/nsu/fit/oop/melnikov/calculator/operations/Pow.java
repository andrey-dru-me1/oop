package ru.nsu.fit.oop.melnikov.calculator.operations;

import java.util.List;

public class Pow extends Operation {

    final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(List<Double> operands) {
        return Math.pow(operands.get(0), operands.get(1));
    }

}
