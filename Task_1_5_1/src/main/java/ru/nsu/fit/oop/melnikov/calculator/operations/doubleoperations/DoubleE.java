package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class DoubleE extends DoubleOperation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(@NotNull List<Object> operands) {
        return Math.E;
    }

    @Override
    public DoubleE clone() {
        return new DoubleE();
    }

    @Override
    public Operation parse(@NotNull String string) {
        if (string.equals("e")) {
            return this.clone();
        }
        return null;
    }

}
