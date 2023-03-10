package ru.nsu.fit.oop.melnikov;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class LoadData {

  private final Set<Cook> cooks;
  private final Set<Courier> couriers;

  public LoadData(String jsonFilename) {

    ObjectMapper objectMapper = new ObjectMapper();

    JsonNode jsonNode;
    try {
      jsonNode = objectMapper.readTree(new File(jsonFilename));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    cooks = new HashSet<>();
    couriers = new HashSet<>();

    for (JsonNode cook : jsonNode.get("cooks")) {
      cooks.add(objectMapper.convertValue(cook, Cook.class));
    }
    for (JsonNode courier : jsonNode.get("couriers")) {
      couriers.add(objectMapper.convertValue(courier, Courier.class));
    }

  }

  public Set<Cook> getCooks() {
    return cooks;
  }

  public Set<Courier> getCouriers() {
    return couriers;
  }

}
