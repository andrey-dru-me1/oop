package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

import java.util.List;

public class DoublePi extends Operation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    public DoubleNumber calculate(List<Value> operands) {
        return new DoubleNumber(Math.PI);
    }

    @Override
    public DoublePi clone() {
        return new DoublePi();
    }

    @Override
    public ru.nsu.fit.oop.melnikov.calculator.operations.Operation parse(String string) {
        if (string.equals("pi")) {
            return this.clone();
        }
        return null;
    }

}
