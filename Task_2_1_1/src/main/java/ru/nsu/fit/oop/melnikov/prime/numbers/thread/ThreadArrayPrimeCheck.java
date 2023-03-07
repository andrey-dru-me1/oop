package ru.nsu.fit.oop.melnikov.prime.numbers.thread;

import java.util.ArrayDeque;
import java.util.Deque;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.prime.numbers.ArrayPrimeCheck;

class ThreadArrayPrimeCheck {

  /**
   * Checks if input array contains non-prime numbers or not. Parallel execution using threads.
   *
   * @param array       array an input array where non-prime numbers will be searched
   * @param threadCount count of threads to work
   * @return true if there is at least one non-prime number and false otherwise
   */
  public static @NotNull Boolean check(int @NotNull [] array, int threadCount, CommonVars res) {

    Deque<Thread> threads = new ArrayDeque<>(threadCount);

    for (int i = 0; i < threadCount; i++) {
      PrimeCheck thread = new PrimeCheck(array, res);
      thread.start();
      threads.add(thread);
    }

    for (Thread i : threads) {
      try {
        i.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    return res.hasCompositeNumber();

  }

  /**
   * Searches a non-prime number from an array and breaks if found.
   */
  private static class PrimeCheck extends Thread {

    private final int[] numbers;
    private final CommonVars res;

    public PrimeCheck(int[] numbers, CommonVars res) {
      this.numbers = numbers;
      this.res = res;
    }

    @Override
    public void run() {

      int index;

      while (!res.hasCompositeNumber() && (index = res.getIndexAndIncrement()) < numbers.length) {

        int number = numbers[index];

        if(ArrayPrimeCheck.isComposite(number, res::hasCompositeNumber)) {
          res.setCompositeNumber();
        }

      }
    }
  }


}