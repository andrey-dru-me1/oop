package ru.nsu.fit.oop.melnikov.prime.numbers.benchmark.test;

import org.junit.jupiter.api.Assertions;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import ru.nsu.fit.oop.melnikov.prime.numbers.SequentialArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.AtomicThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.NoVarsThreadArrayPrimeCheck;

@State(Scope.Benchmark)
public class BenchMarkTest {

  private int[] array;

  @Param({"5", "10", "20", "100", "500", "1000", "5000", "10000"/*, "100000"*/})
  private int size;

  @Setup(Level.Invocation)
  public void setArray() {
    array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = 1000000007;
    }
  }

//  @Benchmark
//  public void benchmarkThreads() {
//    Assertions.assertFalse(new SynchronizedThreadArrayPrimeCheck().check(array));
//  }

  @Benchmark
  public void benchmarkAtomicThreads() {
    Assertions.assertFalse(new AtomicThreadArrayPrimeCheck().check(array));
  }

  @Benchmark
  public void benchmarkNoVarsThreads() {
    Assertions.assertFalse(new NoVarsThreadArrayPrimeCheck().check(array));
  }

  @Benchmark
  public void benchmarkSequential() {
    Assertions.assertFalse(new SequentialArrayPrimeCheck().check(array));
  }

//  @Benchmark
//  public void benchmarkParallelStreams() {
//    Assertions.assertFalse(new ParallelStreamArrayPrimeCheck().check(array));
//  }

}
