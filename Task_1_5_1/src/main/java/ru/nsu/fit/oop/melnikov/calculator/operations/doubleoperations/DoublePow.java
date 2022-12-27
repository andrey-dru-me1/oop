package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class DoublePow extends DoubleOperation {

    private final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(@NotNull List<Object> operands) {
        return Math.pow((Double) operands.get(0), (Double) operands.get(1));
    }

    @Override
    public DoublePow clone() {
        return new DoublePow();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("pow")) {
            return this.clone();
        }
        return null;
    }

}
