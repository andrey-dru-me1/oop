package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;

import java.util.List;

public class Pi extends Operation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(List<Complex> operands) {
        return Complex.ofCartesian(Math.PI, 0);
    }

    @Override
    public Pi clone() {
        return new Pi();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("pi")) {
            return this.clone();
        }
        return null;
    }

}
