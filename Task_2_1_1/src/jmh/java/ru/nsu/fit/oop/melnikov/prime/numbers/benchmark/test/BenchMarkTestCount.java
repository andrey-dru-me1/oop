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
import ru.nsu.fit.oop.melnikov.prime.numbers.ParallelStreamArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.SequentialArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.AtomicThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.NoVarsThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.SynchronizedThreadArrayPrimeCheck;

@State(Scope.Benchmark)
@Measurement(time = 200, timeUnit = TimeUnit.MILLISECONDS)
public class BenchMarkTestCount {

  private int[] array;

  @Param({"5", "10", "20", "100", "500", "1000", "5000"})
  private int size;

  @Setup(Level.Invocation)
  public void setArray() {
    array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = 1000000007;
    }
  }

  @Benchmark
  public void benchmarkSequential() {
    Assertions.assertFalse(new SequentialArrayPrimeCheck().check(array));
  }

  @Benchmark
  public void benchmarkParallelStreams() {
    Assertions.assertFalse(new ParallelStreamArrayPrimeCheck().check(array));
  }

  @Benchmark
  public void benchmarkSynchronizedThreads() {
    Assertions.assertFalse(new SynchronizedThreadArrayPrimeCheck().check(array));
  }

  @Benchmark
  public void benchmarkAtomicThreads() {
    Assertions.assertFalse(new AtomicThreadArrayPrimeCheck().check(array));
  }

  @Benchmark
  public void benchmarkNoVarsThreads() {
    Assertions.assertFalse(new NoVarsThreadArrayPrimeCheck().check(array));
  }

}
