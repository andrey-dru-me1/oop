package ru.nsu.fit.oop.melnikov.pizzeria.employees;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.Order;
import ru.nsu.fit.oop.melnikov.pizzeria.orders.OrderQueue;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

/**
 * Employee that cooks pizzas and brings them to a warehouse.
 */
public class Cook {

  private final int experience;
  private final String name;
  private final Logger log;
  private boolean workStatus = false;

  /**
   * @param experience time in years that cook has been cooking pizzas
   * @param name       name of cook
   */
  @JsonCreator
  public Cook(@JsonProperty("experience") int experience, @JsonProperty("name") String name) {
    this.experience = experience;
    this.name = name;

    log = LoggerFactory.getLogger("Cook " + name);
  }

  public void setWorkStatus(boolean workStatus) {
    this.workStatus = workStatus;
  }

  public int getExperience() {
    return experience;
  }

  public String getName() {
    return name;
  }

  public void work(OrderQueue orders, Warehouse warehouse) {

    log.info("Cook {} starts working", this.getName());

    try {
      while (!Thread.interrupted() && workStatus) {

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

    log.info("Cook {} ends working", this.getName());

  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getExperience());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Cook cookToCompare) {
      return (cookToCompare.getName().equals(this.getName())
          && cookToCompare.getExperience() == this.getExperience());
    } else {
      return false;
    }
  }
}
