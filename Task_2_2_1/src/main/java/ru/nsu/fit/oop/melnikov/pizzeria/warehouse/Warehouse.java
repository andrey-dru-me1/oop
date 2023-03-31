package ru.nsu.fit.oop.melnikov.pizzeria.warehouse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayDeque;
import java.util.Queue;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.timer.WaitingTimer;

/**
 * Presents storage of placing orders and methods of interaction with it.
 */
public class Warehouse {

  private final int capacity;
  private final Queue<Order> orders;
  private boolean isOpened = false;

  @JsonCreator
  public Warehouse(@JsonProperty("capacity") int capacity) {
    this(capacity, new ArrayDeque<>() {
    });
  }

  public Warehouse(int capacity, Queue<Order> orders) {
    this.capacity = capacity;
    this.orders = orders;
  }

  public void setOpened(boolean opened) {
    isOpened = opened;
  }

  public int getCapacity() {
    return capacity;
  }

  /**
   * Put order in warehouse.
   *
   * @param order order to put in warehouse
   * @throws InterruptedException when warehouse close earlier than cook putted pizza in it
   */
  public synchronized void putOrder(Order order) throws InterruptedException {

    while (orders.size() >= capacity) {
      if (!isOpened) {
        throw new InterruptedException();
      }
      wait();
    }

    orders.add(order);
    order.updateStatus("is placed in warehouse");
    notifyAll();
  }

  /**
   * Removes orders from warehouse and returns them. Method can return less than or equal to
   * ordersCount. Method will wait 3000 * ordersCount seconds for orders and if they don't appear
   * then returns already taken orders (which is less than ordersCount). Method can't return 0
   * orders: it will be waiting for at least one of them.
   *
   * @param ordersCount maximum amount of orders to take
   * @return taken from warehouse orders
   * @throws InterruptedException if thread was interrupted during execution
   */
  public synchronized Queue<Order> takeOrders(int ordersCount) throws InterruptedException {

    Queue<Order> takenOrders = new ArrayDeque<>(ordersCount);

    while (ordersCount > 0) {

      WaitingTimer timer = new WaitingTimer(Thread.currentThread());

      if (!takenOrders.isEmpty()) {
        timer.start(ordersCount * 3000L);
      }

      try {

        while (orders.isEmpty()) {
          if (!isOpened) {
            timer.cancel();
            return takenOrders;
          }
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

      Order order = orders.remove();
      order.updateStatus("is taken from warehouse");
      takenOrders.add(order);

      notifyAll();

      ordersCount--;
    }

    return takenOrders;
  }
}
