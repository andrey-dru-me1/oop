package ru.nsu.fit.oop.melnikov.calculator.operations;

import java.util.List;

/**
 * Super-class for each operation that can be supported by calculator.
 */
public abstract class Operation extends Parsable {

  /**
   * Calculates a result of function.
   *
   * @param operands list of operands to pass in operation function
   * @return result of calculation
   */
  public abstract Value calculate(List<Value> operands);

}
