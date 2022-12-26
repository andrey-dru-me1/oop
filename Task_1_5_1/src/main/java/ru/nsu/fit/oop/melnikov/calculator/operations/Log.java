package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class Log extends Operation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Complex> operands) {
        return operands.get(0).log();
    }

    @Override
    public Log clone() {
        return new Log();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("log")) {
            return this.clone();
        }
        return null;
    }

}
