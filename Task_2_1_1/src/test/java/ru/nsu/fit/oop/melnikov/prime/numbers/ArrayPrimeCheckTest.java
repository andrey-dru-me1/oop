package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ArrayPrimeCheckTest {

    @Test
    void test() {

        Assertions.assertFalse(ArrayPrimeCheck.sequential(new int[]{17, 23, 19, 29}));

        Assertions.assertTrue(ArrayPrimeCheck.sequential(new int[]{17, 23, 19, 25}));

        Assertions.assertFalse(ArrayPrimeCheck.sequential(
                new int[]{
                        6997901, 6997927, 6997937, 6997967,
                        6998009, 6998029, 6998039, 6998051, 6998053
                })
        );

    }

}