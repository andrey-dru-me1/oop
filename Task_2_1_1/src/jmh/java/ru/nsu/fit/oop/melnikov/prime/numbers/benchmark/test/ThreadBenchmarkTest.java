package ru.nsu.fit.oop.melnikov.prime.numbers.benchmark.test;

import org.junit.jupiter.api.Assertions;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.AtomicThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.NoVarsThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.SynchronizedThreadArrayPrimeCheck;

@State(Scope.Benchmark)
public class ThreadBenchmarkTest extends BenchMarkTest{

  @Param({"4", "6", "8", "12", "16"})
  private int threadCount;

  @Benchmark
  public void benchmarkThreads() {
    Assertions.assertFalse(SynchronizedThreadArrayPrimeCheck.check(array, threadCount));
  }

  @Benchmark
  public void benchmarkAtomicThreads() {
    Assertions.assertFalse(AtomicThreadArrayPrimeCheck.check(array, threadCount));
  }

  @Benchmark
  public void benchmarkNoVarsThreads() {
    Assertions.assertFalse(new NoVarsThreadArrayPrimeCheck().check(array, threadCount));
  }

}
