package ru.nsu.fit.oop.melnikov.prime.numbers;

import java.util.concurrent.Callable;

public abstract class CommonArrayPrimeCheck implements ArrayPrimeCheck {

  @Override
  public boolean isComposite(int number, Callable<Boolean> isInterrupted) {
    try {
      for (int divider = 2; divider * divider <= number && !isInterrupted.call(); divider++) {
        if (number % divider == 0) {
          return true;
        }
      }
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
