package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.exception.ParseOperationException;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;


public class ComplexLog extends Operation {

  static final private int ARITY = 1;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public ComplexNumber calculate(@NotNull List<Value> operands) {
    if (operands.size() == 1) {
      if (operands.get(0) instanceof ComplexNumber complex1) {
        return new ComplexNumber(complex1.getComplexNumber().log());
      }
    }
    throw new ParseOperationException();
  }

  @Override
  public ComplexLog clone() {
    return new ComplexLog();
  }

  @Override
  public Operation parse(String string) {
    if (string.equals("log")) {
      return this.clone();
    }
    return null;
  }

}
