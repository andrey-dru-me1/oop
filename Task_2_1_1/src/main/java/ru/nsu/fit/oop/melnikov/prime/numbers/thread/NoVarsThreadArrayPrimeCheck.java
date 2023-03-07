package ru.nsu.fit.oop.melnikov.prime.numbers.thread;

import java.util.ArrayDeque;
import java.util.Deque;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.prime.numbers.ArrayPrimeCheck;

public class NoVarsThreadArrayPrimeCheck implements ArrayPrimeCheck {

  @Override
  public @NotNull Boolean check(int @NotNull [] array) {
    return check(array, Runtime.getRuntime().availableProcessors());
  }

  public @NotNull Boolean check(int @NotNull [] array, int threadCount) {
    Deque<PrimeCheck> threads = new ArrayDeque<>(threadCount);

    for (int i = 0; i < threadCount; i++) {
      PrimeCheck thread = new PrimeCheck(
          array.length * i / threadCount,
          array.length * (i + 1) / threadCount,
          array
      );
      thread.start();
      threads.add(thread);
    }

    boolean res = false;

    for (PrimeCheck thread : threads) {
      try {
        thread.join();
        res = res || thread.isHasCompositeNumber();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    return res;
  }

  private static class PrimeCheck extends Thread {

    private final int firstIndex;
    private final int lastIndex;
    private final int[] numbers;
    private boolean hasCompositeNumber;

    public PrimeCheck(int firstIndex, int lastIndex, int[] numbers) {
      this.firstIndex = firstIndex;
      this.lastIndex = lastIndex;
      this.numbers = numbers;
      hasCompositeNumber = false;
    }

    public boolean isHasCompositeNumber() {
      return hasCompositeNumber;
    }

    @Override
    public void run() {
      for (int index = firstIndex; index < lastIndex; index++) {

        int number = numbers[index];

        hasCompositeNumber = hasCompositeNumber || ArrayPrimeCheck.isComposite(number, () -> false);

      }

    }

  }

}
