package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class Sin extends DoubleOperation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(@NotNull List<Object> operands) {
        return Math.sin((Double) operands.get(0));
    }

    @Override
    public Sin clone() {
        return new Sin();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("sin")) {
            return this.clone();
        }
        return null;
    }

}
