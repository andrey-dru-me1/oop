package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Sqr extends Operation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Complex> operands) {
        return operands.get(0).multiply(operands.get(0));
    }

}
