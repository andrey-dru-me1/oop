package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;

import java.util.List;

public class Pow extends Operation {

    final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(List<Complex> operands) {
        return operands.get(0).pow(operands.get(1));
    }

}
