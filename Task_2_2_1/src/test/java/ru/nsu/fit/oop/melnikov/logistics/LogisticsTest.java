package ru.nsu.fit.oop.melnikov.logistics;

import static java.lang.Thread.sleep;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.Courier;
import ru.nsu.fit.oop.melnikov.Cook;
import ru.nsu.fit.oop.melnikov.LoadData;
import ru.nsu.fit.oop.melnikov.Logistics;
import ru.nsu.fit.oop.melnikov.WareHouse;

class LogisticsTest {

  void test(Set<Cook> cooks, Set<Courier> couriers) {
    WareHouse wareHouse = new WareHouse(15);

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

  @Test
  void testSets() {
    Set<Cook> cooks = new HashSet<>();
    Set<Courier> couriers = new HashSet<>();

    cooks.add(new Cook(5));
    couriers.add(new Courier(2));

    test(cooks, couriers);
  }

  @Test
  void testJson() {
    LoadData loadData = new LoadData("data.json");
    Set<Cook> cooks = loadData.getCooks();
    Set<Courier> couriers = loadData.getCouriers();

    test(cooks, couriers);
  }

}