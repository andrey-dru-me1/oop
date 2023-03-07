package ru.nsu.fit.oop.melnikov.prime.numbers.benchmark.test;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import ru.nsu.fit.oop.melnikov.prime.numbers.ParallelStreamArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.AtomicThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.NoVarsThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.SynchronizedThreadArrayPrimeCheck;

@State(Scope.Benchmark)
@Warmup(time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(time = 700, timeUnit = TimeUnit.MILLISECONDS)
public class BenchmarkThreadCount {

  private int[] array;

  @Setup(Level.Invocation)
  public void setArray() {
    array = new int[1000];
    for (int i = 0; i < 1000; i++) {
      array[i] = 1000000007;
    }
  }

  @Param({"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"})
  private int threadCount;

  @Benchmark
  public void benchmarkSynchronizedThreads() {
    Assertions.assertFalse(SynchronizedThreadArrayPrimeCheck.check(array, threadCount));
  }

  @Benchmark
  public void benchmarkAtomicThreads() {
    Assertions.assertFalse(AtomicThreadArrayPrimeCheck.check(array, threadCount));
  }

  @Benchmark
  public void benchmarkNoVarsThreads() {
    Assertions.assertFalse(NoVarsThreadArrayPrimeCheck.check(array, threadCount));
  }

  @Benchmark
  public void benchmarkParallelStreams() {
    System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(threadCount));
    Assertions.assertFalse(new ParallelStreamArrayPrimeCheck().check(array));
    System.clearProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
  }

}
