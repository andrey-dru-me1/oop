package ru.nsu.fit.oop.melnikov.prime.numbers.thread;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.prime.numbers.ArrayPrimeCheck;

public class SynchronizedThreadArrayPrimeCheck implements ArrayPrimeCheck {

    private static class SynchronizedCommonVars implements CommonVars {
        private boolean hasCompositeNumber;

        public int currentIndex;

        private SynchronizedCommonVars(boolean hasCompositeNumber) {
            this.hasCompositeNumber = hasCompositeNumber;
            this.currentIndex = 0;
        }

        @Override
        public boolean hasCompositeNumber() {
            return hasCompositeNumber;
        }

        @Override
        public void setCompositeNumber() {
            hasCompositeNumber = true;
        }

        @Override
        public int getIndexAndIncrement() {
            synchronized(this) {
                return currentIndex++;
            }
        }
    }

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

    public static @NotNull Boolean check(int @NotNull [] array, int threadCount) {
        return ThreadArrayPrimeCheck
                .check(
                        array,
                        threadCount,
                        new SynchronizedCommonVars(false)
                );

    }
}
