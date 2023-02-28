package ru.nsu.fit.oop.melnikov.calculator.operations.doubleoperations;

import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class DoubleNumber extends Value {

  static final private int ARITY = 0;
  private final java.lang.Double value;

  public DoubleNumber() {
    this(null);
  }

  public DoubleNumber(java.lang.Double value) {
    this.value = value;
  }

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public DoubleNumber clone() {
    return new DoubleNumber(this.value);
  }

  @Override
  public DoubleNumber parse(String string) {
    double value;
    try {
      value = Double.parseDouble(string);
    } catch (NumberFormatException exception) {
      return null;
    }
    return new DoubleNumber(value);
  }

  @Override
  public String getValue() {
    return value.toString();
  }

  public Double getDoubleValue() {
    return value;
  }

}