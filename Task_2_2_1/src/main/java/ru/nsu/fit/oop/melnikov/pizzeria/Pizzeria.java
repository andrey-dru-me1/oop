package ru.nsu.fit.oop.melnikov.pizzeria;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import ru.nsu.fit.oop.melnikov.pizzeria.employees.Cook;
import ru.nsu.fit.oop.melnikov.pizzeria.employees.Courier;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.OrderQueue;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Pizza;
import ru.nsu.fit.oop.melnikov.pizzeria.threads.ThreadController;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

public class Pizzeria {

  private final String name;
  private final Collection<Cook> cooks;
  private final Collection<Courier> couriers;
  private final Warehouse warehouse;
  private final OrderQueue orders;
  private final ThreadController threadController;
  public Pizzeria(
      String name, @NotNull Collection<Cook> cooks, Collection<Courier> couriers, Warehouse warehouse) {
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

  public Collection<Cook> getCooks() {
    return cooks;
  }

  public Collection<Courier> getCouriers() {
    return couriers;
  }

  public Warehouse getWarehouse() {
    return warehouse;
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
