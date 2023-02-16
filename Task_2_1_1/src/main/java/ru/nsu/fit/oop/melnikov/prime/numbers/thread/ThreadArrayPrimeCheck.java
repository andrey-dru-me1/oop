package ru.nsu.fit.oop.melnikov.prime.numbers.thread;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class ThreadArrayPrimeCheck <T extends CommonVars> {

        private class PrimeCheck extends Thread {

            private final int[] numbers;
            private final T res;

            public PrimeCheck(int[] numbers, T res) {
                this.numbers = numbers;
                this.res = res;
            }

            @Override
            public void run() {

                int index;

                while (!res.hasCompositeNumber() && (index = res.getIndexAndIncrement()) < numbers.length) {

                    int number = numbers[index];

                    for (int divider = 2; divider * divider <= number && !res.hasCompositeNumber(); divider++) {
                        if (number % divider == 0) {
                            res.setCompositeNumber();
                            break;
                        }
                    }

                }
            }
        }

        /**
         * Checks if input array contains non-prime numbers or not.
         * Parallel execution using threads.
         *
         * @param array       array an input array where non-prime numbers will be searched
         * @param threadCount count of threads to work
         * @return true if there is at least one non-prime number and false otherwise
         */
        public @NotNull Boolean check(int @NotNull [] array, int threadCount, T res) {

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

            return res.hasCompositeNumber();

        }


}
