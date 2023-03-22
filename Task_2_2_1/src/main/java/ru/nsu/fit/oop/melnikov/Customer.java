package ru.nsu.fit.oop.melnikov;

import org.jetbrains.annotations.NotNull;

public class Customer {

  public void orderPizza(@NotNull Pizzeria pizzeria, String pizzaName) {

    Order order = pizzeria.orderPizza(new Pizza(pizzaName));

    synchronized (order) {
      try {
        order.wait();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    order.updateStatus("is delivered!");
  }
}
