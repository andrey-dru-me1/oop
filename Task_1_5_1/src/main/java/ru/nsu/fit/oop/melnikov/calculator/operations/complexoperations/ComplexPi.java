package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import java.util.List;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class ComplexPi extends Operation {

  private final static int ARITY = 0;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public ComplexNumber calculate(List<Value> operands) {
    return new ComplexNumber(Math.PI);
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
