package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class Deg extends DoubleOperation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(@NotNull List<Object> operands) {
        return ((Double) operands.get(0)) / 180.0 * Math.PI;
    }

    @Override
    public Deg clone() {
        return new Deg();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("deg")) {
            return this.clone();
        }
        return null;
    }

}
