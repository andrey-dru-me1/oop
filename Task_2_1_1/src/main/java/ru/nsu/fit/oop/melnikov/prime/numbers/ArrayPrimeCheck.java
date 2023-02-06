package ru.nsu.fit.oop.melnikov.prime.numbers;

import org.jetbrains.annotations.NotNull;

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

//    public static boolean threadSolution(int @NotNull [] array) {
//
//        boolean res = false;
//
//        for(int i : array) {
//
//            (new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for(int divider = 2; divider * divider <= i; divider++) {
//                        if(i % divider == 0) {
//                        }
//                    }
//                }
//            }))
//
//        }
//
//        return false;
//
//    }

}
