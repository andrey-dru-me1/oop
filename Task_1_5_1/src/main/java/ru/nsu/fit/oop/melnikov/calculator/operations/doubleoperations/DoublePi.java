package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class DoublePi extends DoubleOperation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(List<Object> operands) {
        return Math.PI;
    }

    @Override
    public DoublePi clone() {
        return new DoublePi();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("pi")) {
            return this.clone();
        }
        return null;
    }

}
