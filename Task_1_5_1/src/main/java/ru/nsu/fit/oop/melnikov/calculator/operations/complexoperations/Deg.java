package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class Deg extends ComplexOperation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Object> operands) {
        return ((Complex) operands.get(0)).divide(180).multiply(Math.PI);
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
