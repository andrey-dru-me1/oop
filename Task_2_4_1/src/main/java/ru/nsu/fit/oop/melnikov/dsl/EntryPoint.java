package ru.nsu.fit.oop.melnikov.dsl;

import java.io.IOException;
import java.util.Collection;
import ru.nsu.fit.oop.melnikov.dsl.dto.GroupsConfig;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;

public class EntryPoint {

  public static void main(String[] args) throws IOException {
    Collection<Group> groups = GroupsConfig.parse("dsl_configs/groups.groovy");
    System.out.println(groups);
  }
}
