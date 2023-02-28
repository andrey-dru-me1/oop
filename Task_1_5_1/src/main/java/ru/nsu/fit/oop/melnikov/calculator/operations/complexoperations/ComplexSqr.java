package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.exception.ParseOperationException;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class ComplexSqr extends Operation {

  private final static int ARITY = 1;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public ComplexNumber calculate(@NotNull List<Value> operands) {
    if (operands.size() == 1) {
      if (operands.get(0) instanceof ComplexNumber complex1) {
        return new ComplexNumber(complex1.getComplexNumber().multiply(complex1.getComplexNumber()));
      }
    }
    throw new ParseOperationException();
  }

  @Override
  public ComplexSqr clone() {
    return new ComplexSqr();
  }

  @Override
  public Operation parse(String string) {
    if (string.equals("sqr")) {
      return this.clone();
    }
    return null;
  }

}
