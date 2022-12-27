package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class Cos extends DoubleOperation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(@NotNull List<Object> operands) {
        return Math.cos((Double) operands.get(0));
    }

    @Override
    public Cos clone() {
        return new Cos();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("cos")) {
            return this.clone();
        }
        return null;
    }

}
