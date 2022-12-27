package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class ComplexPow extends ComplexOperation {

    private final static int ARITY = 2;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Object> operands) {
        return ((Complex) operands.get(0)).pow((Complex) operands.get(1));
    }

    @Override
    public ComplexPow clone() {
        return new ComplexPow();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("pow")) {
            return this.clone();
        }
        return null;
    }

}
