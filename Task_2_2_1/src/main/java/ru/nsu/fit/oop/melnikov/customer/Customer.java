package ru.nsu.fit.oop.melnikov.customer;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Pizza;

/**
 * Outside person that wants to order pizza.
 * If he doesn't want to be named we would call him "Anonymous".
 */
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
        while (!order.isDone()) {
          order.wait();
        }
      }

      log.info("Customer {} receives his {}!", this.name, pizzaName);

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      order.updateStatus("is interrupted!");
    }
  }
}
