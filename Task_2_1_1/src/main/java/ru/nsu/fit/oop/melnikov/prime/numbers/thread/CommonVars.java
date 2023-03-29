package ru.nsu.fit.oop.melnikov.prime.numbers.thread;

/**
 * Class describing common variables for parallel threads to use. It has two variables: - boolean
 * hasCompositeNumber; - int currentIndex. An interface gives user three methods to use these
 * variables.
 */
public interface CommonVars {

  /**
   * Checks if flag hasCompositeNumber is set by any thread.
   *
   * @return true if any thread has found composite number in an array
   */
  boolean hasCompositeNumber();

  /**
   * Sets hasCompositeNumber flag with true. It signals that some thread found composite number in
   * an array.
   */
  void setCompositeNumber();

  /**
   * Typical "return currentIndex++" method but implemented with critical section solution.
   *
   * @return current index
   */
  int getIndexAndIncrement();

}
