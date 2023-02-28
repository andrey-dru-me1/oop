package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import java.util.List;
import org.apache.commons.numbers.complex.Complex;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.calculator.operations.Operation;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class I extends Operation {

  private final static int ARITY = 0;

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public ComplexNumber calculate(@NotNull List<Value> operands) {
    return new ComplexNumber(Complex.ofCartesian(0, 1));
  }

  @Override
  public I clone() {
    return new I();
  }

  @Override
  public Operation parse(@NotNull String string) {
    if (string.equals("i")) {
      return this.clone();
    }
    return null;
  }
}
