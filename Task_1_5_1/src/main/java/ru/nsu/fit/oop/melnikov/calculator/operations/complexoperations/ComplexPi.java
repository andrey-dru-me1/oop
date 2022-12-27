package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

import java.util.List;

public class ComplexPi extends ComplexOperation {

    private final static int ARITY = 0;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    protected Complex calculate(List<Object> operands) {
        return Complex.ofCartesian(Math.PI, 0);
    }

    @Override
    public ComplexPi clone() {
        return new ComplexPi();
    }

    @Override
    public Operation parse(String string) {
        if (string.equals("pi")) {
            return this.clone();
        }
        return null;
    }

}
