package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Multiply extends Operation {

    private final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Complex> operands) {
        return operands.get(0).multiply(operands.get(1));
    }

    @Override
    public Multiply clone() {
        return new Multiply();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("*")) {
            return this.clone();
        }
        return null;
    }

}
