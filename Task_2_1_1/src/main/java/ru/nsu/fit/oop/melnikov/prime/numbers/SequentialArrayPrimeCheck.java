package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Class that contains three different methods of non-prime number searching: - Sequential execution
 * - Parallel execution using threads - Parallel execution using parallelStream
 */
public class SequentialArrayPrimeCheck implements ArrayPrimeCheck {

  /**
   * Checks if input array contains non-prime numbers or not. Sequential execution.
   *
   * @param array an input array where non-prime numbers will be searched
   * @return true if there is at least one non-prime number and false otherwise
   */
  @Override
  @Contract(pure = true)
  public @NotNull Boolean check(int @NotNull [] array) {

    for (int i : array) {
      if(ArrayPrimeCheck.isComposite(i, () -> false)) {
        return true;
      }
    }

    return false;

  }

}
