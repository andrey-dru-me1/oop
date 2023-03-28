package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.exception.ParseOperationException;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;


public class DoubleLog extends Operation {

  private static final int ARITY = 1;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public DoubleNumber calculate(@NotNull List<Value> operands) {
    if (operands.size() == 1) {
      if (operands.get(0) instanceof DoubleNumber double1) {
        return new DoubleNumber(Math.log(double1.getDoubleValue()));
      }
    }
    throw new ParseOperationException();
  }

  @Override
  public DoubleLog clone() {
    return new DoubleLog();
  }

  @Override
  public ru.nsu.fit.oop.melnikov.calculator.operations.Operation parse(String string) {
    if (string.equals("log")) {
      return this.clone();
    }
    return null;
  }

}
