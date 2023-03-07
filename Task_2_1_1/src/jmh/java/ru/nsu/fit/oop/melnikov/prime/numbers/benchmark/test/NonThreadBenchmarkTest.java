package ru.nsu.fit.oop.melnikov.prime.numbers.benchmark.test;

import org.junit.jupiter.api.Assertions;
import org.openjdk.jmh.annotations.Benchmark;
import ru.nsu.fit.oop.melnikov.prime.numbers.ParallelStreamArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.SequentialArrayPrimeCheck;

public class NonThreadBenchmarkTest extends BenchMarkTest {

  @Benchmark
  public void benchmarkSequential() {
    Assertions.assertFalse(new SequentialArrayPrimeCheck().check(array));
  }

  @Benchmark
  public void benchmarkParallelStreams() {
    Assertions.assertFalse(new ParallelStreamArrayPrimeCheck().check(array));
  }

}
