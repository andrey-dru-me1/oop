package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayPrimeCheck {

    /**
     * Checks if input array contains non-prime numbers or not.
     * Sequential execution.
     *
     * @param array an input array where non-prime numbers will be searched
     * @return true if there is at least one non-prime number and false otherwise
     */
    public static boolean sequential(int @NotNull [] array) {

        for (int i : array) {
            for (int divider = 2; divider * divider <= i; divider++) {
                if (i % divider == 0) {
                    return true;
                }
            }
        }

        return false;

    }

    public static boolean threadSolution(int @NotNull [] array) {

        int cores = Runtime.getRuntime().availableProcessors();

        class CommonVar {
            public volatile boolean var;

            public volatile int currentIndex;

            private CommonVar(boolean var) {
                this.var = var;
                this.currentIndex = 0;
            }

        }
        class PrimeCheck extends Thread {

            private final int[] numbers;
            private final CommonVar res;

            public PrimeCheck(int[] numbers, CommonVar res) {
                this.numbers = numbers;
                this.res = res;
            }

            @Override
            public void run() {

                int number;

                while (!res.var) {

                    synchronized (res) {
                        if (res.currentIndex >= numbers.length)
                            break;
                        number = numbers[res.currentIndex++];
                    }

                    for (int divider = 2; divider * divider <= number && !res.var; divider++) {
                        if (number % divider == 0) {
                            res.var = true;
                            break;
                        }
                    }

                }
            }
        }

        CommonVar res = new CommonVar(false);

        Deque<Thread> threads = new ArrayDeque<>(cores);

        for (int i = 0; i < cores; i++) {
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

        return res.var;

    }

}
