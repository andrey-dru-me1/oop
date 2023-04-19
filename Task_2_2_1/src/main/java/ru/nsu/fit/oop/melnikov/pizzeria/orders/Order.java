package ru.nsu.fit.oop.melnikov.pizzeria.orders;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class contains all the information about single order and logs any status change.
 */
public class Order{

  public static final Logger log  = LoggerFactory.getLogger("Order");

  private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);

  private final int id;
  private final Pizza pizza;
  private boolean done = false;

  public Order(Pizza pizza) {
    this.id = ID_COUNTER.getAndIncrement();
    this.pizza = pizza;
    this.updateStatus("is received");
  }

  public boolean isDone() {
    return done;
  }

  public int getId() {
    return id;
  }

  public Pizza getPizza() {
    return pizza;
  }

  /**
   * Logs new status with order id and pizza name specified.
   *
   * @param status new status
   */
  public void updateStatus(String status) {
    status = this.getId() + ": " + this.getPizza().name() + " order " + status;
    log.info(status);
  }

  /**
   * Sets done flag and notifies customer that pizza has derived.
   */
  public synchronized void delivered() {
    updateStatus("is delivered!");
    this.done = true;
    notifyAll();
  }

}
