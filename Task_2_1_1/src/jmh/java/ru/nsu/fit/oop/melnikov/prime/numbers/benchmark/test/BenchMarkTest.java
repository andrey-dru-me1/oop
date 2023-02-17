package ru.nsu.fit.oop.melnikov.prime.numbers.benchmark.test;

import org.junit.jupiter.api.Assertions;
import org.openjdk.jmh.annotations.*;
import ru.nsu.fit.oop.melnikov.prime.numbers.ParallelStreamArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.SequentialArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.AtomicThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.SynchronizedThreadArrayPrimeCheck;

@State(Scope.Benchmark)
public class BenchMarkTest {

    private int[] array;

    @Param({"5", "10", "20", "100", "500", "1000", "5000", "10000", "100000"})
    private int size;

    @Setup(Level.Invocation)
    public void setArray() {
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = 1000000007;
        }
    }

    @Benchmark
    public void benchmarkThreads() {
        Assertions.assertFalse(new SynchronizedThreadArrayPrimeCheck().check(array));
    }

    @Benchmark
    public void benchmarkAtomicThreads() {
        Assertions.assertFalse(new AtomicThreadArrayPrimeCheck().check(array));
    }

    @Benchmark
    public void benchmarkSequential() {
        Assertions.assertFalse(new SequentialArrayPrimeCheck().check(array));
    }

    @Benchmark
    public void benchmarkParallelStreams() {
        Assertions.assertFalse(new ParallelStreamArrayPrimeCheck().check(array));
    }

}
