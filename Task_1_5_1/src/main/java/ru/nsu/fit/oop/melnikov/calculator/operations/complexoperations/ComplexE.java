package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class ComplexE extends ComplexOperation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Object> operands) {
        return Complex.ofCartesian(Math.E, 0);
    }

    @Override
    public ComplexE clone() {
        return new ComplexE();
    }

    @Override
    public Operation parse(@NotNull String string) {
        if (string.equals("e")) {
            return this.clone();
        }
        return null;
    }

}
