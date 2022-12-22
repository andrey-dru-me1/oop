package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;

import java.util.List;

public class E extends Operation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(List<Complex> operands) {
        return Complex.ofCartesian(Math.E, 0);
    }

}
