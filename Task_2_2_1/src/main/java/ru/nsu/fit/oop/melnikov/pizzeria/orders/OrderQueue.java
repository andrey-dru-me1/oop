package ru.nsu.fit.oop.melnikov.pizzeria.orders;

import java.util.ArrayDeque;

public class OrderQueue extends ArrayDeque<Order> {

  /**
   * Returns order if at least one of them exists in queue or wait for new order otherwise.
   *
   * @return new order
   * @throws InterruptedException if thread was interrupted
   */
  public synchronized Order take() throws InterruptedException {

    while(this.size() == 0) {
      wait();
    }

    return this.remove();

  }

  /**
   * Adds order to a queue and notifies single thread if someone (cook) is waiting for it.
   *
   * @param order order to add
   */
  public synchronized void addAndNotify(Order order) {

    this.add(order);
    order.updateStatus("is putted in order queue");
    notify();

  }

}
