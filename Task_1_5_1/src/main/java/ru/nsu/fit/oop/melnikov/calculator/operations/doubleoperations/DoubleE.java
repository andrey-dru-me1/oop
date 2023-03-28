package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class DoubleE extends Operation {

  private static final int ARITY = 0;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public DoubleNumber calculate(@NotNull List<Value> operands) {
    return new DoubleNumber(Math.E);
  }

  @Override
  public DoubleE clone() {
    return new DoubleE();
  }

  @Override
  public ru.nsu.fit.oop.melnikov.calculator.operations.Operation parse(@NotNull String string) {
    if (string.equals("e")) {
      return this.clone();
    }
    return null;
  }

}
