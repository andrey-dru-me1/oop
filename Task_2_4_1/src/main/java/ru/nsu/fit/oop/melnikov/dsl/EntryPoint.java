package ru.nsu.fit.oop.melnikov.dsl;

import java.io.IOException;
import java.util.Collection;
import ru.nsu.fit.oop.melnikov.dsl.dto.GroupsConfig;
import ru.nsu.fit.oop.melnikov.dsl.dto.TasksConfig;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;
import ru.nsu.fit.oop.melnikov.dsl.model.Task;

public class EntryPoint {

  public static void main(String[] args) throws IOException {
    Collection<Group> groups = GroupsConfig.parse("dsl_configs/groups.groovy");
    Collection<Task> tasks = TasksConfig.parse("dsl_configs/tasks.groovy");
    System.out.println(groups);
    System.out.println(tasks);
  }
}
