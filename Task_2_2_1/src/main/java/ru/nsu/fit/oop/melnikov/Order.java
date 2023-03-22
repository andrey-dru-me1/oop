package ru.nsu.fit.oop.melnikov;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Order {

  public static final Logger log  = LoggerFactory.getLogger("Order");

  private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);

  private final int id;
  private final Pizza pizza;
  private String status;

  Order(Pizza pizza) {
    this.id = ID_COUNTER.getAndIncrement();
    this.pizza = pizza;
    this.updateStatus("is received");
  }

  public int getId() {
    return id;
  }

  public Pizza getPizza() {
    return pizza;
  }

  public String getStatus() {
    return status;
  }

  public void updateStatus(String status) {
    status = this.getId() + ": " + this.getPizza().name() + " order " + status;
    this.status = status;
    log.info(status);
  }

  public synchronized void delivered() {
    updateStatus("is delivered!");
    notifyAll();
  }

}
