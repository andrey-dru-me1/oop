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

    @Override
    public E clone() {
        return new E();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("e")) {
            return this.clone();
        }
        return null;
    }

}
