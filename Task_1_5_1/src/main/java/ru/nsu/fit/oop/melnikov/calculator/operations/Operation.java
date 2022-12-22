package ru.nsu.fit.oop.melnikov.calculator.operations;

import org.apache.commons.numbers.complex.Complex;

import java.util.List;

/**
 * Super-class for each operation that can be supported by calculator.
 */
public abstract class Operation {

    /**
     * returns arity of operation.
     *
     * @return arity of operation
     */
    abstract public int getArity();

    /**
     * Calculates a result of function.
     *
     * @param operands list of operands to pass in operation function
     * @return result of calculation
     */
    abstract protected Complex calculate(List<Complex> operands);

    /**
     * Applies current operation to operands.
     *
     * @param operands list of operands to pass in operation function
     * @return result of calculation
     */
    public Complex apply(List<Complex> operands) throws WrongOperandsCountException {
        if (operands.size() != getArity()) throw new WrongOperandsCountException();
        return calculate(operands);
    }

    static public class WrongOperandsCountException extends Exception {
    }

}
