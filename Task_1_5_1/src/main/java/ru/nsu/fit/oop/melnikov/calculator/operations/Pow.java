package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Pow extends Operation {

    private final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Complex> operands) {
        return operands.get(0).pow(operands.get(1));
    }

    @Override
    public Pow clone() {
        return new Pow();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("pow")) {
            return this.clone();
        }
        return null;
    }

}
