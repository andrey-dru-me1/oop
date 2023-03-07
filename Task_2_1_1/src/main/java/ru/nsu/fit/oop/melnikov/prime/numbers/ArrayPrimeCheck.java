package ru.nsu.fit.oop.melnikov.prime.numbers;

import java.util.concurrent.Callable;
import org.jetbrains.annotations.NotNull;

/**
 * Checks if array contains non-prime numbers.
 */
public interface ArrayPrimeCheck {

  @NotNull Boolean check(int @NotNull [] array);

  static boolean isComposite(int number, Callable<Boolean> isInterrupted) {
    try {
      for (int divider = 2; divider * divider <= number && !isInterrupted.call(); divider++) {
        if (number % divider == 0) {
          return true;
        }
      }
      return false;
    } catch(Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

}
