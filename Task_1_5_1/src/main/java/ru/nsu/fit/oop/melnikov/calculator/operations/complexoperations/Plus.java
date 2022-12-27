package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class Plus extends ComplexOperation {

    private final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Object> operands) {
        return ((Complex) operands.get(0)).add((Complex) operands.get(1));
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
