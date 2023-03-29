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

/** Presents Pizzeria with sets of cooks and couriers which will service customers. */
public class Pizzeria {

  private final String name;
  private final Collection<Cook> cooks;
  private final Collection<Courier> couriers;
  private final Warehouse warehouse;
  private final OrderQueue orders;
  private final ThreadController threadController;

  public Pizzeria(
      String name,
      @NotNull Collection<Cook> cooks,
      Collection<Courier> couriers,
      Warehouse warehouse) {
    this.name = name;
    this.cooks = cooks;
    this.couriers = couriers;
    this.warehouse = warehouse;
    this.orders = new OrderQueue();
    this.threadController = new ThreadController();

    for (Cook cook : cooks) {
      threadController.addThread(new Thread(() -> cook.work(orders, warehouse)));
    }

    for (Courier courier : couriers) {
      threadController.addThread(new Thread(() -> courier.work(warehouse)));
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

  /**
   * Make all the cooks and couriers start working. Cooks are waiting for orders and couriers are
   * waiting for pizzas in warehouse.
   */
  public void start() {
    threadController.startAll();
  }

  /** Make all the cooks and couriers finish their work and close a pizzeria. */
  public void close() {
    threadController.interruptAll();
  }

  /**
   * Add order in order queue from where cooks are taking orders to cook.
   *
   * @param pizza pizza to cook
   * @return order formed from request
   */
  public @NotNull Order orderPizza(Pizza pizza) {

    Order order = new Order(pizza);
    orders.addAndNotify(order);
    return order;
  }
}
