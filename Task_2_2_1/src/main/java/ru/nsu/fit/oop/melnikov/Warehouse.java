package ru.nsu.fit.oop.melnikov;

import static java.lang.Math.min;

public class Warehouse {

  private final int capacity;
  private volatile int pizzasAmount;

  public Warehouse(int capacity) {
    this.capacity = capacity;
    pizzasAmount = 0;
  }

  public synchronized void putPizza() throws IndexOutOfBoundsException {
    if (pizzasAmount >= capacity) {
      throw new IndexOutOfBoundsException();
    }
    pizzasAmount++;
  }

  public synchronized int takePizzas(int amount) throws IndexOutOfBoundsException {
    if (pizzasAmount <= 0) {
      throw new IndexOutOfBoundsException();
    }
    int pizzasToTake = min(amount, pizzasAmount);
    pizzasAmount -= pizzasToTake;
    return pizzasToTake;
  }

}
