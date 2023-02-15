package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class SynchronizedThreadArrayPrimeCheck implements ArrayPrimeCheck {

    /**
     * Checks if input array contains non-prime numbers or not.
     * Parallel execution using threads.
     *
     * @param array an input array where non-prime numbers will be searched
     * @return true if there is at least one non-prime number and false otherwise
     */
    @Override
    public @NotNull Boolean check(int @NotNull [] array) {
        return check(array, Runtime.getRuntime().availableProcessors());
    }

    public static Boolean check(int @NotNull [] array, int threadCount) {

        class CommonVars {
            public boolean hasCompositeNumber;

            public int currentIndex;

            private CommonVars(boolean hasCompositeNumber) {
                this.hasCompositeNumber = hasCompositeNumber;
                this.currentIndex = 0;
            }

        }
        class PrimeCheck extends Thread {

            private final int[] numbers;
            private final CommonVars res;

            public PrimeCheck(int[] numbers, CommonVars res) {
                this.numbers = numbers;
                this.res = res;
            }

            @Override
            public void run() {

                int number;

                while (!res.hasCompositeNumber) {

                    synchronized (res) {
                        if (res.currentIndex >= numbers.length)
                            break;
                        number = numbers[res.currentIndex++];
                    }

                    for (int divider = 2; divider * divider <= number && !res.hasCompositeNumber; divider++) {
                        if (number % divider == 0) {
                            res.hasCompositeNumber = true;
                            break;
                        }
                    }

                }
            }
        }

        CommonVars res = new CommonVars(false);

        Deque<Thread> threads = new ArrayDeque<>(threadCount);

        for (int i = 0; i < threadCount; i++) {
            PrimeCheck thread = new PrimeCheck(array, res);
            thread.start();
            threads.add(thread);
        }

        for (Thread i : threads) {
            try {
                i.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return res.hasCompositeNumber;

    }
}
