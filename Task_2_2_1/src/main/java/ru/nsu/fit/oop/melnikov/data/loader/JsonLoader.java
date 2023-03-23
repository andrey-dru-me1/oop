package ru.nsu.fit.oop.melnikov.data.loader;

import com.fasterxml.jackson.databind.JsonNode;import com.fasterxml.jackson.databind.ObjectMapper;import java.io.File;import java.io.IOException;import java.util.Collection;
import java.util.HashSet;import ru.nsu.fit.oop.melnikov.pizzeria.Pizzeria;import ru.nsu.fit.oop.melnikov.pizzeria.employees.Cook;
import ru.nsu.fit.oop.melnikov.pizzeria.employees.Courier;
import ru.nsu.fit.oop.melnikov.pizzeria.warehouse.Warehouse;

public class JsonLoader implements Loader {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public Pizzeria extractPizzeriaFromFile(File jsonFile) throws IOException {

    JsonNode pizzeria = MAPPER.readTree(jsonFile).get("pizzeria");

    String name = pizzeria.get("name").textValue();

    Warehouse warehouse = MAPPER.convertValue(pizzeria.get("warehouse"), Warehouse.class);

    JsonNode employees = pizzeria.get("employees");

    Collection<Cook> cooks = new HashSet<>();
    for (JsonNode node: employees.get("cooks")) {
      cooks.add(MAPPER.convertValue(node, Cook.class));
    }

    Collection<Courier> couriers = new HashSet<>();
    for (JsonNode node: employees.get("couriers")) {
      couriers.add(MAPPER.convertValue(node, Courier.class));
    }

    return new Pizzeria(name, cooks, couriers, warehouse);

  }
}
