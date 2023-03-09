package ru.nsu.fit.oop.melnikov.logistics;

import static java.lang.Thread.sleep;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.courier.Courier;
import ru.nsu.fit.oop.melnikov.kitchen.Cook;
import ru.nsu.fit.oop.melnikov.warehouse.WareHouse;

class LogisticsTest {

  @Test
  void test() {
    Set<Cook> cooks = new HashSet<>();
    Set<Courier> couriers = new HashSet<>();
    WareHouse wareHouse = new WareHouse(15);

    cooks.add(new Cook(5));
    couriers.add(new Courier(2));

    Logistics logistics = new Logistics(cooks, couriers, wareHouse);

    logistics.orderNewPizza();

    try {
      sleep(6000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    logistics.orderNewPizza();

    try {
      sleep(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

}