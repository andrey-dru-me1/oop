package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;

public abstract class DoubleOperation extends Operation {

    @Override
    protected Class<?> getOperandClass() {
        return Double.class;
    }

}
