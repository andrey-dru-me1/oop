package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class Plus extends DoubleOperation {

    private final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(@NotNull List<Object> operands) {
        return ((Double) operands.get(0)) + ((Double) operands.get(1));
    }

    @Override
    public Plus clone() {
        return new Plus();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("+")) {
            return this.clone();
        }
        return null;
    }

}
