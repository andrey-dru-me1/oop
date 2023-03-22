package ru.nsu.fit.oop.melnikov;

import java.util.ArrayDeque;
import java.util.Queue;

public class Warehouse {

  protected final int capacity;
  protected final Queue<Order> pizzas;

  public Warehouse(int capacity) {
    this(capacity, new ArrayDeque<>() {});
  }

  public Warehouse(int capacity, Queue<Order> pizzas) {
    this.capacity = capacity;
    this.pizzas = pizzas;
  }

  public int getCapacity() {
    return capacity;
  }

  public Queue<Order> getOrders() {
    return pizzas;
  }

  public synchronized void putOrder(Order order) throws InterruptedException {

    while (pizzas.size() >= capacity) {
      wait();
    }

    pizzas.add(order);
    order.updateStatus("is placed in warehouse");
    notifyAll();

  }

  public synchronized Order takeOrder() throws InterruptedException {

    while (pizzas.size() == 0) {
      wait();
    }

    Order order = pizzas.poll();
    order.updateStatus("is taken from warehouse");
    notifyAll();

    return order;

  }
}
