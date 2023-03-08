package ru.nsu.fit.oop.melnikov.logistics;

import static java.lang.Thread.sleep;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.oop.melnikov.kitchen.Cook;

class LogisticsTest {

  @Test
  void test() {
    Set<Cook> cooks = new HashSet<>();
    cooks.add(new Cook(1));
    Logistics logistics = new Logistics(cooks);
    logistics.orderNewPizza();
    try {
      sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    logistics.orderNewPizza();
    try {
      sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}