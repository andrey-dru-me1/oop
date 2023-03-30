package ru.nsu.fit.oop.melnikov.pizzeria.employees;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Queue;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

/**
 * Employee that delivers orders from warehouse to customers.
 */
public class Courier {

  private final int trunkSize;
  private final String name;
  private boolean endWork = false;

  /**
   * @param trunkSize maximum orders amount to take
   * @param name      name of courier
   */
  @JsonCreator
  public Courier(@JsonProperty("trunkSize") int trunkSize, @JsonProperty("name") String name) {
    this.trunkSize = trunkSize;
    this.name = name;
  }

  public int getTrunkSize() {
    return trunkSize;
  }

  public String getName() {
    return name;
  }

  public void work(Warehouse warehouse) {

    try {

      while (!Thread.interrupted() && !endWork) {

        Queue<Order> orders = warehouse.takeOrders(this.trunkSize);

        while (!orders.isEmpty()) {
          Order order = orders.remove();
          order.updateStatus("is on its way, delivering by " + this.name);

          // Delivering
          //noinspection BusyWait
          sleep(3000);

          order.delivered();
        }
      }

    } catch (InterruptedException e) {
      currentThread().interrupt();
    }
  }

  public void setEndWork() {
    endWork = true;
  }
}
