package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

public abstract class ComplexOperation extends Operation {

    @Override
    protected Class<?> getOperandClass() {
        return Complex.class;
    }

}
