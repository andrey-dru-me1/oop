package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Number extends Operation {

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
    protected Complex calculate(@NotNull List<Complex> operands) {
        return Complex.ofCartesian(this.value, 0);
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