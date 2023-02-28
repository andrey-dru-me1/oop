package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.exception.ParseOperationException;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class DoublePlus extends Operation {

  static final private int ARITY = 2;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public DoubleNumber calculate(@NotNull List<Value> operands) {
    if (operands.size() == 2) {
      if (operands.get(0) instanceof DoubleNumber double1 && operands.get(
          1) instanceof DoubleNumber double2) {
        return new DoubleNumber(double1.getDoubleValue() + (double2.getDoubleValue()));
      }
    }
    throw new ParseOperationException();
  }

  @Override
  public DoublePlus clone() {
    return new DoublePlus();
  }

  @Override
  public ru.nsu.fit.oop.melnikov.calculator.operations.Operation parse(String string) {
    if (string.equals("+")) {
      return this.clone();
    }
    return null;
  }

}
