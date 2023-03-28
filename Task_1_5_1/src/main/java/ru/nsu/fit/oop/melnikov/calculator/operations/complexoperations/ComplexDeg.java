package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.exception.ParseOperationException;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class ComplexDeg extends Operation {

  private static final int ARITY = 1;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public ComplexNumber calculate(@NotNull List<Value> operands) {
    if (operands.size() == 1) {
      if (operands.get(0) instanceof ComplexNumber complex1) {
        return new ComplexNumber(complex1.getComplexNumber().divide(180).multiply(Math.PI));
      }
    }
    throw new ParseOperationException();
  }

  @Override
  public ComplexDeg clone() {
    return new ComplexDeg();
  }

  @Override
  public Operation parse(String string) {
    if (string.equals("deg")) {
      return this.clone();
    }
    return null;
  }

}
