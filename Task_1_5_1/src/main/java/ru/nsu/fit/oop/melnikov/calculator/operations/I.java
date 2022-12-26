package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;

import java.util.List;

public class I extends Operation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(List<Complex> operands) {
        return Complex.ofCartesian(0, 1);
    }

    @Override
    public I clone() {
        return new I();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("i")) {
            return this.clone();
        }
        return null;
    }
}
