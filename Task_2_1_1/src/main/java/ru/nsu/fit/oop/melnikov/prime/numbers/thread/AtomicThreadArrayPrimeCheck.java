package ru.nsu.fit.oop.melnikov.prime.numbers.thread;

import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.prime.numbers.ArrayPrimeCheck;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicThreadArrayPrimeCheck implements ArrayPrimeCheck {

    private static class AtomicCommonVars implements CommonVars  {
        public boolean hasCompositeNumber;

        public final AtomicInteger currentIndex;

        private AtomicCommonVars(boolean hasCompositeNumber) {
            this.hasCompositeNumber = hasCompositeNumber;
            this.currentIndex = new AtomicInteger(0);
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
            return currentIndex.getAndIncrement();
        }
    }

    @Override
    public @NotNull Boolean check(int @NotNull [] array) {
        return check(array, Runtime.getRuntime().availableProcessors());
    }

    /**
     * Checks if input array contains non-prime numbers or not.
     * Parallel execution using threads.
     *
     * @param array       array an input array where non-prime numbers will be searched
     * @param threadCount count of threads to work
     * @return true if there is at least one non-prime number and false otherwise
     */
    public @NotNull Boolean check(int @NotNull [] array, int threadCount) {

        return new ThreadArrayPrimeCheck<AtomicCommonVars>()
                .check(array, threadCount, new AtomicCommonVars(false));

    }

}
