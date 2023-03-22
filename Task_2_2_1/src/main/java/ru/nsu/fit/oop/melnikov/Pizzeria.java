package ru.nsu.fit.oop.melnikov;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Pizzeria {

  private final String name;
  private final Set<Cook> cooks;
  private final Set<Courier> couriers;
  private final Warehouse warehouse;
  private final OrderQueue orders;
  private final ThreadController threadController;

  public Pizzeria(
      String name, @NotNull Set<Cook> cooks, Set<Courier> couriers, Warehouse warehouse) {
    this.name = name;
    this.cooks = cooks;
    this.couriers = couriers;
    this.warehouse = warehouse;
    this.orders = new OrderQueue();
    this.threadController = new ThreadController();

    for (Cook cook : cooks) {
      threadController.addCookThread(new Thread(() -> cook.work(orders, warehouse)));
    }

    for (Courier courier : couriers) {
      threadController.addCourierThread(new Thread(() -> courier.work(warehouse)));
    }

  }

  public String getName() {
    return name;
  }

  public void start() {
    threadController.startAll();
  }

  public void close() {
    threadController.interruptAll();
  }

  public @NotNull Order orderPizza(Pizza pizza) {

    Order order = new Order(pizza);
    orders.addAndNotify(order);
    return order;
  }
}
