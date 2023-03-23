package ru.nsu.fit.oop.melnikov.pizzeria.employees;

import static java.lang.Thread.currentThread;import static java.lang.Thread.sleep;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.OrderQueue;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

public record Cook(int experience, String name) {

  public void work(OrderQueue orders, Warehouse warehouse) {

    try {
      while (!Thread.interrupted()) {

        Order order = orders.take();
        order.updateStatus("is started cooking by cook " + this.name);

        // Cooking
        //noinspection BusyWait
        sleep(5000 / experience);
        order.updateStatus("is cooked!");

        warehouse.putOrder(order);
      }
    } catch (InterruptedException ignore) {
      currentThread().interrupt();
    }
  }

}
