package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.exception.ParseOperationException;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

import java.util.List;

public class DoubleDeg extends Operation {

    private final static int ARITY = 1;

    @Override
    public int getArity() {
        return ARITY;
    }

    @Override
    public DoubleNumber calculate(@NotNull List<Value> operands) {
        if (operands.size() == 1) {
            if (operands.get(0) instanceof DoubleNumber double1) {
                return new DoubleNumber(double1.getDoubleValue() / 180 * Math.PI);
            }
        }
        throw new ParseOperationException();
    }

    @Override
    public DoubleDeg clone() {
        return new DoubleDeg();
    }

    @Override
    public ru.nsu.fit.oop.melnikov.calculator.operations.Operation parse(String string) {
        if (string.equals("deg")) {
            return this.clone();
        }
        return null;
    }

}
