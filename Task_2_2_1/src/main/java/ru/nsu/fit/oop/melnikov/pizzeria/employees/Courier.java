package ru.nsu.fit.oop.melnikov.pizzeria.employees;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.util.Queue;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

public record Courier(int trunkSize, String name) {

  public void work(Warehouse warehouse) {

    try {

      while (!Thread.interrupted()) {

        Queue<Order> orders = warehouse.takeOrders(this.trunkSize());

        while (!orders.isEmpty()) {
          Order order = orders.remove();
          order.updateStatus("is on its way, delivering by " + this.name());

          // Delivering
          sleep(1000);

          order.delivered();
        }
      }

    } catch (InterruptedException e) {
      currentThread().interrupt();
    }
  }
}
