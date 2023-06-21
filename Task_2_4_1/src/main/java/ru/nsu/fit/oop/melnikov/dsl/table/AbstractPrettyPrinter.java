package ru.nsu.fit.oop.melnikov.dsl.table;

public class AbstractPrettyPrinter {
  protected static final int NAME_COL_LEN = 15;

  protected AbstractPrettyPrinter() {}

  protected static String normalizeString(String string, Integer length) {
    if (string.length() >= length) {
      return string.substring(0, length);
    }
    return string + " ".repeat(length - string.length());
  }
}
