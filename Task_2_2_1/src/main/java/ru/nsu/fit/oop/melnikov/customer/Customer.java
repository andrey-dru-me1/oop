package ru.nsu.fit.oop.melnikov.customer;

import ru.nsu.fit.oop.melnikov.logistics.Logistics;

public class Customer {

  private final Logistics logistics;

  public Customer(Logistics logistics) {
    this.logistics = logistics;
  }

  public void makeOrder() {
    logistics.orderNewPizza();
  }

}
