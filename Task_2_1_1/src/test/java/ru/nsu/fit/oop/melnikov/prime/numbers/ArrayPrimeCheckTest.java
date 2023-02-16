package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.AtomicThreadArrayPrimeCheck;
import ru.nsu.fit.oop.melnikov.prime.numbers.thread.SynchronizedThreadArrayPrimeCheck;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArrayPrimeCheckTest {

    @ParameterizedTest
    @MethodSource("getFunctions")
    void test(@NotNull ArrayPrimeCheck type) {
        Assertions.assertFalse(type.check(new int[]{17, 23, 19, 29}));

        Assertions.assertTrue(type.check(new int[]{17, 23, 19, 25}));

        Assertions.assertFalse(type.check(
                new int[]{
                        6997901, 6997927, 6997937, 6997967,
                        6998009, 6998029, 6998039, 6998051, 6998053
                })
        );
    }

    @Contract(pure = true)
    private @NotNull Stream<ArrayPrimeCheck> getFunctions() {
        return Stream.of(
                new SequentialArrayPrimeCheck(),
                new ParallelStreamArrayPrimeCheck(),
                new AtomicThreadArrayPrimeCheck(),
                new SynchronizedThreadArrayPrimeCheck()
        );
    }

}