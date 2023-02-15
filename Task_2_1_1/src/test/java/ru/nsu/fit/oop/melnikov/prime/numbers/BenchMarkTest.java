package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.junit.jupiter.api.Assertions;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@Fork(3)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class BenchMarkTest {

    private final int[] array;

    public BenchMarkTest() {
        array = new int[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = 1000000007;
        }
    }

    @Benchmark
    public void benchmarkSequential() {
        Assertions.assertFalse(new SequentialArrayPrimeCheck().check(array));
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
    public void benchmarkParallelStreams() {
        Assertions.assertFalse(new ParallelStreamArrayPrimeCheck().check(array));
    }

}
