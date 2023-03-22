package ru.nsu.fit.oop.melnikov.pizzeria.orders;

import java.util.ArrayDeque;

public class OrderQueue extends ArrayDeque<Order> {

  public synchronized Order take() throws InterruptedException {

    while(this.size() == 0) {
      wait();
    }

    return this.poll();

  }

  public synchronized void addAndNotify(Order order) {

    this.add(order);
    order.updateStatus("is putted in order queue");
    notify();

  }

}
