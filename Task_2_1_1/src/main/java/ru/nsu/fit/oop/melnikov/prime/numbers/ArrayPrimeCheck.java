package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Class that contains three different methods of non-prime number searching:
 * - Sequential execution
 * - Parallel execution using threads
 * - Parallel execution using parallelStream
 */
public class ArrayPrimeCheck {

    /**
     * Checks if input array contains non-prime numbers or not.
     * Sequential execution.
     *
     * @param array an input array where non-prime numbers will be searched
     * @return true if there is at least one non-prime number and false otherwise
     */
    @Contract(pure = true)
    public static @NotNull Boolean sequential(int @NotNull [] array) {

        for (int i : array) {
            for (int divider = 2; divider * divider <= i; divider++) {
                if (i % divider == 0) {
                    return true;
                }
            }
        }

        return false;

    }


    /**
     * Checks if input array contains non-prime numbers or not.
     * Parallel execution using threads.
     *
     * @param array an input array where non-prime numbers will be searched
     * @return true if there is at least one non-prime number and false otherwise
     */
    public static Boolean threadSolution(int @NotNull [] array) {
        return threadSolution(array, Runtime.getRuntime().availableProcessors());
    }

    /**
     * Checks if input array contains non-prime numbers or not.
     * Parallel execution using threads.
     *
     * @param array       array an input array where non-prime numbers will be searched
     * @param threadCount count of threads to work
     * @return true if there is at least one non-prime number and false otherwise
     */
    public static Boolean threadSolution(int @NotNull [] array, int threadCount) {

        class CommonVars {
            public volatile boolean hasCompositeNumber;

            public volatile int currentIndex;

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

    /**
     * Checks if input array contains non-prime numbers or not.
     * Parallel execution using parallelStream.
     *
     * @param array an input array where non-prime numbers will be searched
     * @return true if there is at least one non-prime number and false otherwise
     */
    public static @NotNull Boolean parallelStreamSolution(int @NotNull [] array) {

        final boolean[] res = {false};

        Arrays.stream(array).parallel().forEach(
                (int x) -> {
                    for (int divider = 2; divider * divider <= x && !res[0]; divider++) {
                        if (x % divider == 0) {
                            res[0] = true;
                            break;
                        }
                    }
                }
        );

        return res[0];

    }

}
