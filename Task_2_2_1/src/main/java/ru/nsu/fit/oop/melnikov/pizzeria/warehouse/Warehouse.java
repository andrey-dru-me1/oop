package ru.nsu.fit.oop.melnikov.pizzeria.warehouse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayDeque;
import java.util.Queue;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;

public class Warehouse {

  private final int capacity;
  private final Queue<Order> pizzas;

  @JsonCreator
  public Warehouse(@JsonProperty("capacity") int capacity) {
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

    while (pizzas.isEmpty()) {
      wait();
    }

    Order order = pizzas.poll();
    order.updateStatus("is taken from warehouse");
    notifyAll();

    return order;

  }
}
