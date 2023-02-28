package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class ComplexE extends Operation {

  private final static int ARITY = 0;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public ComplexNumber calculate(@NotNull List<Value> operands) {
    return new ComplexNumber(Math.E);
  }

  @Override
  public ComplexE clone() {
    return new ComplexE();
  }

  @Override
  public Operation parse(@NotNull String string) {
    if (string.equals("e")) {
      return this.clone();
    }
    return null;
  }

}
