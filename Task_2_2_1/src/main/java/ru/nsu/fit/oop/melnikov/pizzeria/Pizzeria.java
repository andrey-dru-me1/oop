package ru.nsu.fit.oop.melnikov.pizzeria;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.fit.oop.melnikov.pizzeria.employees.Cook;
import ru.nsu.fit.oop.melnikov.pizzeria.employees.Courier;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.OrderQueue;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Pizza;
import ru.nsu.fit.oop.melnikov.pizzeria.threads.ThreadController;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

/**
 * Presents Pizzeria with sets of cooks and couriers which will service customers.
 */
public class Pizzeria {

  private final String name;
  private final Collection<Cook> cooks;
  private final Collection<Courier> couriers;
  private final Warehouse warehouse;
  private final OrderQueue orders;
  private final ThreadController threadController;

  private boolean workStatus = false;
  private final Logger log;

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
    log = LoggerFactory.getLogger(this.name);
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
   * waiting for pizzas in warehouse. If pizzeria has already been started then does nothing.
   */
  public void start() {
    if (workStatus) {
      return;
    }

    this.setWorkStatus(true);

    for (Cook cook : cooks) {
      threadController.addCookThread(new Thread(() -> cook.work(orders, warehouse)));
    }
    for (Courier courier : couriers) {
      threadController.addCourierThread(new Thread(() -> courier.work(warehouse)));
    }
    threadController.startAll();

    log.info("Pizzeria {} is opened", this.getName());
  }

  private void setWorkStatus(boolean status) {
    for (Cook cook : cooks) {
      cook.setWorkStatus(status);
    }
    for (Courier courier : couriers) {
      courier.setWorkStatus(status);
    }
    orders.setOpened(status);
    warehouse.setOpened(status);
    workStatus = status;
  }

  /**
   * Make all the cooks and couriers finish their work and close a pizzeria. If pizzeria is already
   * closed then does nothing.
   */
  public void evacuate() {
    if (!workStatus) {
      return;
    }
    threadController.interruptAll();
    this.setWorkStatus(false);
    log.info("Pizzeria {} is evacuated", this.getName());
  }

  /**
   * Wait for all the cooks and couriers finishing their work and close a pizzeria. If pizzeria is
   * already closed does nothing.
   */
  public void stop() {
    if (!workStatus) {
      return;
    }
    try {
      for (Cook cook : cooks) {
        cook.setWorkStatus(false);
      }
      orders.setOpened(false);
      synchronized (orders) {
        orders.notifyAll();
      }
      for (Thread cookThread : threadController.getCookThreads()) {
        cookThread.join();
      }
      for (Courier courier : couriers) {
        courier.setWorkStatus(false);
      }
      warehouse.setOpened(false);
      synchronized (warehouse) {
        warehouse.notifyAll();
      }
      for (Thread courierThread : threadController.getCourierThreads()) {
        courierThread.join();
      }
      threadController.clearAll();
      this.workStatus = false;
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    log.info("Pizzeria {} is closed", this.getName());
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
