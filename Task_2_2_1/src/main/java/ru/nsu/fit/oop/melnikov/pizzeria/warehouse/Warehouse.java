package ru.nsu.fit.oop.melnikov.pizzeria.warehouse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayDeque;
import java.util.Queue;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.timer.WaitingTimer;

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

  public synchronized Queue<Order> takeOrders(int ordersCount) throws InterruptedException {

    Queue<Order> takenOrders = new ArrayDeque<>(ordersCount);

    while (ordersCount > 0) {

      WaitingTimer timer = new WaitingTimer(Thread.currentThread());

      if(!takenOrders.isEmpty()) timer.start(ordersCount * 3000L);

      try {

        while (pizzas.isEmpty()) {
          wait();
        }

      } catch (InterruptedException e) {

        if (timer.isTimerTriggered()) {
          break;
        } else {
          throw e;
        }

      } finally {
        timer.cancel();
      }

      Order order = pizzas.remove();
      order.updateStatus("is taken from warehouse");
      takenOrders.add(order);

      notifyAll();

      ordersCount--;
    }

    return takenOrders;
  }
}
