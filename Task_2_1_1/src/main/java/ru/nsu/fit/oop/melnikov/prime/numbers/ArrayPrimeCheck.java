package ru.nsu.fit.oop.melnikov.prime.numbers;

import java.util.concurrent.Callable;
import org.jetbrains.annotations.NotNull;

/** Checks if array contains non-prime numbers. */
public interface ArrayPrimeCheck {

  @NotNull
  Boolean check(int @NotNull [] array);

  boolean isComposite(int number, Callable<Boolean> isInterrupted);
}
