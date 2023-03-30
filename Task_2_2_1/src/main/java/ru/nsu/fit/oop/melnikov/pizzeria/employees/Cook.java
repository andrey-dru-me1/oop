package ru.nsu.fit.oop.melnikov.pizzeria.employees;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.OrderQueue;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

/**
 * Employee that cooks pizzas and brings them to a warehouse.
 */
public class Cook {

  private final int experience;
  private final String name;
  private boolean endWork = false;

  /**
   * @param experience time in years that cook has been cooking pizzas
   * @param name       name of cook
   */
  @JsonCreator
  public Cook(@JsonProperty("experience") int experience, @JsonProperty("name") String name) {
    this.experience = experience;
    this.name = name;
  }

  public int getExperience() {
    return experience;
  }

  public String getName() {
    return name;
  }

  public void work(OrderQueue orders, Warehouse warehouse) {

    try {
      while (!Thread.interrupted() && !endWork) {

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

  public void setEndWork() {
    endWork = true;
  }

}
