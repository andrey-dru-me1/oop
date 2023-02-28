package ru.nsu.fit.oop.melnikov.calculator.operations.complexoperations;

import org.apache.commons.numbers.complex.Complex;
import ru.nsu.fit.oop.melnikov.calculator.operations.Value;

public class ComplexNumber extends Value {

  static final private int ARITY = 0;
  private final org.apache.commons.numbers.complex.Complex value;

  public ComplexNumber() {
    this(0.0);
  }

  public ComplexNumber(java.lang.Double value) {
    this.value = org.apache.commons.numbers.complex.Complex.ofCartesian(value, 0);
  }

  public ComplexNumber(org.apache.commons.numbers.complex.Complex value) {
    this.value = value;
  }

  @Override
  public int getArity() {
    return ARITY;
  }

  @Override
  public ComplexNumber clone() {
    return new ComplexNumber(this.value.getReal());
  }

  @Override
  public ComplexNumber parse(String string) {
    double value;
    try {
      value = java.lang.Double.parseDouble(string);
    } catch (NumberFormatException exception) {
      return null;
    }
    return new ComplexNumber(value);
  }

  @Override
  public String getValue() {
    return value.toString();
  }

  public Complex getComplexNumber() {
    return value;
  }

}