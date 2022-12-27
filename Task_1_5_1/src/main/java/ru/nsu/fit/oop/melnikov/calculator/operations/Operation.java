package ru.nsu.fit.oop.melnikov.calculator.operations;

import com.sun.jdi.InvalidTypeException;
import org.jetbrains.annotations.NotNull;

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
    abstract protected Object calculate(List<Object> operands);

    abstract protected Class<?> getOperandClass();

    /**
     * Applies current operation to operands.
     *
     * @param operands list of operands to pass in operation function
     * @return result of calculation
     */
    public Object apply(@NotNull List<Object> operands) {
        if (operands.size() != getArity()) throw new WrongOperandsCountException();
        Class<?> clazz = this.getOperandClass();
        for (int i = 0; i < getArity(); i++) {
            if (!(operands.get(i).getClass() == clazz)) {
                throw new RuntimeException(new InvalidTypeException());
            }
        }
        return calculate(operands);
    }

    /**
     * Clones current object and returns a clone.
     *
     * @return new object which is the same as this object
     */
    public abstract Operation clone();

    /**
     * @param string string to parse (single word)
     * @return null, if operation not intended for parsing input string,
     * and parsed operation object otherwise
     */
    public abstract Operation parse(String string);

    static public class WrongOperandsCountException extends RuntimeException {
    }

    static public class ParseOperationException extends RuntimeException {
    }

}
