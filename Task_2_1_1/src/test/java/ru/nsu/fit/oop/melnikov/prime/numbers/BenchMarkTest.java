package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.function.Function;

@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 0)
@Measurement(iterations = 1)
public class BenchMarkTest {

    private int @NotNull [] readFile(String filename) {

        Scanner scanner;
        try {
            scanner = new Scanner(
                    new File(filename)
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Deque<Integer> queue = new ArrayDeque<>();

        while (scanner.hasNextInt()) {
            queue.add(scanner.nextInt());
        }

        int[] array = new int[queue.size()];

        for (int i = 0; !queue.isEmpty(); i++) {
            array[i] = queue.pop();
        }

        return array;
    }

    private void test(@NotNull Function<int[], Boolean> foo) {
        Assertions.assertTrue(foo.apply(readFile("prime_numbers.txt")));
    }

    @Benchmark
    public void benchmarkSequential() {
        test(ArrayPrimeCheck::sequential);
    }

    @Benchmark
    public void benchmarkThreads() {
        test(ArrayPrimeCheck::threadSolution);
    }

    @Benchmark
    public void benchmarkParallelStreams() {
        test(ArrayPrimeCheck::parallelStreamSolution);
    }

}
