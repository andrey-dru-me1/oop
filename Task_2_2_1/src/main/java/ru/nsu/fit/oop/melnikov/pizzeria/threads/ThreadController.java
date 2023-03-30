package ru.nsu.fit.oop.melnikov.pizzeria.threads;

import java.util.HashSet;
import java.util.Set;

public class ThreadController {

  private final Set<Thread> cookThreads;
  private final Set<Thread> courierThreads;

  public ThreadController() {
    this.cookThreads = new HashSet<>();
    this.courierThreads = new HashSet<>();
  }

  public Set<Thread> getCookThreads() {
    return cookThreads;
  }

  public Set<Thread> getCourierThreads() {
    return courierThreads;
  }

  public void addCookThread(Thread cookThread) {
    cookThreads.add(cookThread);
  }

  public void addCourierThread(Thread courierThread) {
    courierThreads.add(courierThread);
  }

  public void startAll() {

    for (Thread thread : cookThreads) {
      thread.start();
    }

    for (Thread thread : courierThreads) {
      thread.start();
    }

  }

  public void interruptAll() {

    for (Thread thread : cookThreads) {
      thread.interrupt();
    }

    for (Thread thread : courierThreads) {
      thread.interrupt();
    }

  }

}
