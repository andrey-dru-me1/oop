package ru.nsu.fit.oop.melnikov.prime.numbers.thread;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.prime.numbers.ArrayPrimeCheck;

public class SynchronizedThreadArrayPrimeCheck implements ArrayPrimeCheck {

  /**
   * Checks if input array contains non-prime numbers or not. Parallel execution using specified
   * count of threads.
   *
   * @param array       an input array where non-prime numbers will be searched
   * @param threadCount count of threads to use
   * @return true if there is at least one non-prime number and false otherwise
   */
  public static @NotNull Boolean check(int @NotNull [] array, int threadCount) {
    return ThreadArrayPrimeCheck
        .check(
            array,
            threadCount,
            new SynchronizedCommonVars(false)
        );

  }

  /**
   * Checks if input array contains non-prime numbers or not. Parallel execution using threads.
   *
   * @param array an input array where non-prime numbers will be searched
   * @return true if there is at least one non-prime number and false otherwise
   */
  @Override
  public @NotNull Boolean check(int @NotNull [] array) {
    return check(array, Runtime.getRuntime().availableProcessors());
  }

  private static class SynchronizedCommonVars implements CommonVars {

    private boolean hasCompositeNumber;

    private int currentIndex;

    private SynchronizedCommonVars(boolean hasCompositeNumber) {
      this.hasCompositeNumber = hasCompositeNumber;
      this.currentIndex = 0;
    }

    @Override
    public boolean hasCompositeNumber() {
      return hasCompositeNumber;
    }

    @Override
    public void setCompositeNumber() {
      hasCompositeNumber = true;
    }

    @Override
    public int getIndexAndIncrement() {
      synchronized (this) {
        return currentIndex++;
      }
    }
  }
}
