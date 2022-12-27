package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class Number extends DoubleOperation {

    private final static int ARITY = 0;
    private final Double value;

    public Number() {
        this(null);
    }

    public Number(Double value) {
        this.value = value;
    }

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Double calculate(@NotNull List<Object> operands) {
        return this.value;
    }

    @Override
    public Number clone() {
        return new Number(this.value);
    }

    @Override
    public Operation parse(String string) {
        double value;
        try {
            value = Double.parseDouble(string);
        } catch (NumberFormatException exception) {
            return null;
        }
        return new Number(value);
    }

}