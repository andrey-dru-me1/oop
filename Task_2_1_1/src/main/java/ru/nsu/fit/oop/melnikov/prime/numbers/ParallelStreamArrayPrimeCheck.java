package ru.nsu.fit.oop.melnikov.prime.numbers;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

public class ParallelStreamArrayPrimeCheck implements ArrayPrimeCheck {

  /**
   * Checks if input array contains non-prime numbers or not. Parallel execution using
   * parallelStream.
   *
   * @param array an input array where non-prime numbers will be searched
   * @return true if there is at least one non-prime number and false otherwise
   */
  @Override
  public @NotNull Boolean check(int @NotNull [] array) {

    final boolean[] res = {false};

    Arrays.stream(array).parallel().forEach(
        (int x) -> res[0] = res[0] || ArrayPrimeCheck.isComposite(x, () -> res[0])
    );

    return res[0];

  }

}
