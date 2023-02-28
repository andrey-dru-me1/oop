package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.exception.ParseOperationException;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class ComplexPow extends Operation {

  private final static int ARITY = 2;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public ComplexNumber calculate(@NotNull List<Value> operands) {
    if (operands.size() == 2) {
      if (operands.get(0) instanceof ComplexNumber complex1 && operands.get(
          1) instanceof ComplexNumber complex2) {
        return new ComplexNumber(complex1.getComplexNumber().pow(complex2.getComplexNumber()));
      }
    }
    throw new ParseOperationException();
  }

  @Override
  public ComplexPow clone() {
    return new ComplexPow();
  }

  @Override
  public Operation parse(String string) {
    if (string.equals("pow")) {
      return this.clone();
    }
    return null;
  }

}
