package ru.nsu.fit.oop.melnikov;

import java.util.Collection;import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  public static void main(String[] args) {

    Logger log = LoggerFactory.getLogger("Main");

    Set<Cook> cooks = new HashSet<>();
    Set<Courier> couriers = new HashSet<>();

    cooks.add(new Cook(1, "John"));
    couriers.add(new Courier(1, "James"));

    Warehouse warehouse = new Warehouse(1);

    Pizzeria pizzeria = new Pizzeria("Mamma mia", cooks, couriers, warehouse);
    pizzeria.start();

    Scanner scanner = new Scanner(System.in);

    Collection<Thread> customerThreads = new HashSet<>();

    boolean isLoop = true;
    while (isLoop) {

      String command = scanner.next();

      switch (command) {
        case "order" -> {
          String name = scanner.next();
          Customer customer = new Customer();
          Thread thread = new Thread(() -> customer.orderPizza(pizzeria, name));
          thread.start();
          customerThreads.add(thread);
        }
        case "exit" -> {
          isLoop = false;
          pizzeria.close();
          for (Thread customerThread : customerThreads) {
              customerThread.interrupt();
          }
        }
        default -> log.error("Unknown command");
      }
    }
  }
}
