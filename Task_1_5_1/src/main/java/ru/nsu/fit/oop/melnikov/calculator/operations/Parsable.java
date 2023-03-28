package ru.nsu.fit.oop.melnikov.calculator.operations;

public abstract class Parsable {

  /**
   * returns arity of operation.
   *
   * @return arity of operation
   */
  public abstract int getArity();

  /**
   * Clones current object and returns a clone.
   *
   * @return new object which is the same as this object
   */
  public abstract Parsable clone();

  /**
   * @param string string to parse (single word)
   * @return null, if operation not intended for parsing input string, and parsed operation object
   * otherwise
   */
  public abstract Parsable parse(String string);

}
