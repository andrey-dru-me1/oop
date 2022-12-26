package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Sqrt extends Operation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Complex> operands) {
        return operands.get(0).sqrt();
    }

    @Override
    public Sqrt clone() {
        return new Sqrt();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("sqrt")) {
            return this.clone();
        }
        return null;
    }

}
