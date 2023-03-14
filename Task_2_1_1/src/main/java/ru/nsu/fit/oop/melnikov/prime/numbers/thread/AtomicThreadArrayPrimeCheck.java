package ru.nsu.fit.oop.melnikov.prime.numbers.thread;

import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;

public class AtomicThreadArrayPrimeCheck extends ThreadArrayPrimeCheck {

  /**
   * Checks if input array contains non-prime numbers or not. Parallel execution using threads and
   * AtomicInteger to solve critical sections.
   *
   * @param array array an input array where non-prime numbers will be searched
   * @param threadCount count of threads to work
   * @return true if there is at least one non-prime number and false otherwise
   */
  public @NotNull Boolean check(int @NotNull [] array, int threadCount) {

    return this.check(array, threadCount, new AtomicCommonVars(false));
  }

  /**
   * Checks if input array contains non-prime numbers or not. Parallel execution using threads and
   * AtomicInteger to solve critical sections. Uses number of parallel threads equals to number of
   * available processors.
   *
   * @param array array an input array where non-prime numbers will be searched
   * @return true if there is at least one non-prime number and false otherwise
   */
  @Override
  public @NotNull Boolean check(int @NotNull [] array) {
    return this.check(array, Runtime.getRuntime().availableProcessors());
  }

  private static class AtomicCommonVars implements CommonVars {

    public final AtomicInteger currentIndex;
    public boolean hasCompositeNumber;

    private AtomicCommonVars(boolean hasCompositeNumber) {
      this.hasCompositeNumber = hasCompositeNumber;
      this.currentIndex = new AtomicInteger(0);
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
      return currentIndex.getAndIncrement();
    }
  }
}
