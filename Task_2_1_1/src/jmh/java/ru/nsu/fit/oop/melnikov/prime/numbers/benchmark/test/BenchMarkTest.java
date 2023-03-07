package ru.nsu.fit.oop.melnikov.prime.numbers.benchmark.test;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class BenchMarkTest {

  protected int[] array;

  @Param({"5", "10", "20", "100", "500", "1000", "5000"})
  protected int size;

  @Setup(Level.Invocation)
  public void setArray() {
    array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = 1000000007;
    }
  }

}
