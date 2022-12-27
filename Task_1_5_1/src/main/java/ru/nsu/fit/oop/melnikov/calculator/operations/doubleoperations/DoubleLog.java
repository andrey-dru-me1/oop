package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;


public class DoubleLog extends DoubleOperation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(@NotNull List<Object> operands) {
        return Math.log((Double) operands.get(0));
    }

    @Override
    public DoubleLog clone() {
        return new DoubleLog();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("log")) {
            return this.clone();
        }
        return null;
    }

}
