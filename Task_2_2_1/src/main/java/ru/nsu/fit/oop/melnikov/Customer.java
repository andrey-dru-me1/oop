package ru.nsu.fit.oop.melnikov;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer {

  private final String name;
  private final Logger log = LoggerFactory.getLogger("Customer");

  public Customer() {
    this("Anonymous");
  }

  public Customer(String name) {
    this.name = name;
  }

  public void orderPizza(@NotNull Pizzeria pizzeria, String pizzaName) {

    Order order = pizzeria.orderPizza(new Pizza(pizzaName));

    try {
      synchronized (order) {
        order.wait();
      }

      log.info("Customer {} receives his {}!", this.name, pizzaName);

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      order.updateStatus("is interrupted!");
    }
  }
}
