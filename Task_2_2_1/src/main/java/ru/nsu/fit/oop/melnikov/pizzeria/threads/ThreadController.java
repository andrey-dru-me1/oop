package ru.nsu.fit.oop.melnikov.pizzeria.threads;

import java.util.Collection;
import java.util.HashSet;

/**
 * Class that contains and takes all the control over employee threads.
 */
public class ThreadController {

  private final Collection<Thread> threads;

  public ThreadController() {
    this.threads = new HashSet<>();
  }

  public void addThread(Thread thread) {
    threads.add(thread);
  }

  public void startAll() {
    for (Thread thread : threads) {
      thread.start();
    }
  }

  public void interruptAll() {
    for (Thread thread : threads) {
      thread.interrupt();
    }
  }

}
