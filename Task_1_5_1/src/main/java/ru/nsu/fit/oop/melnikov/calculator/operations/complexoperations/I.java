package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class I extends ComplexOperation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(@NotNull List<Object> operands) {
        return Complex.ofCartesian(0, 1);
    }

    @Override
    public I clone() {
        return new I();
    }

    @Override
    public Operation parse(@NotNull String string) {
        if (string.equals("i")) {
            return this.clone();
        }
        return null;
    }
}
