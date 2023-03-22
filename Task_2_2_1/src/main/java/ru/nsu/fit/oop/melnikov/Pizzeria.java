package ru.nsu.fit.oop.melnikov;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Pizzeria {

  private final String name;
  private final Set<Cook> cooks;
  private final Set<Courier> couriers;
  private final Warehouse warehouse;
  private final OrderQueue orders;

  public Pizzeria(
      String name, @NotNull Set<Cook> cooks, Set<Courier> couriers, Warehouse warehouse) {
    this.name = name;
    this.cooks = cooks;
    this.couriers = couriers;
    this.warehouse = warehouse;
    this.orders = new OrderQueue();
  }

  public void start() {
    for (Cook cook : cooks) {
      new Thread(() -> cook.work(orders, warehouse)).start();
    }

    for (Courier courier : couriers) {
      new Thread(() -> courier.work(warehouse)).start();
    }
  }

  public @NotNull Order orderPizza(Pizza pizza) {

    Order order = new Order(pizza);
    orders.addAndNotify(order);
    return order;
  }
}
