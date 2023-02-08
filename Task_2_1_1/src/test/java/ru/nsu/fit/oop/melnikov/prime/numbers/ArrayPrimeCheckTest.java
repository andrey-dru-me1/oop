package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArrayPrimeCheckTest {

    @ParameterizedTest
    @MethodSource("getFunctions")
    void test(@NotNull Function<int[], Boolean> foo) {
        Assertions.assertFalse(foo.apply(new int[]{17, 23, 19, 29}));

        Assertions.assertTrue(foo.apply(new int[]{17, 23, 19, 25}));

        Assertions.assertFalse(foo.apply(
                new int[]{
                        6997901, 6997927, 6997937, 6997967,
                        6998009, 6998029, 6998039, 6998051, 6998053
                })
        );
    }

    @Contract(pure = true)
    private @NotNull Stream<Function<int[], Boolean>> getFunctions() {
        return Stream.of(
                ArrayPrimeCheck::sequential,
                ArrayPrimeCheck::threadSolution,
                ArrayPrimeCheck::parallelStreamSolution
        );
    }

}