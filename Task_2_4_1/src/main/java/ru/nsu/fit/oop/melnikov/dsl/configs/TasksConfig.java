package ru.nsu.fit.oop.melnikov.dsl.configs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import groovy.lang.Closure;
import ru.nsu.fit.oop.melnikov.dsl.model.Task;

public class TasksConfig extends AbstractConfig {

  private final Collection<Task> tasks;

  private TasksConfig() {
    tasks = new ArrayList<>();
  }

  public static Collection<Task> parse(String filePath) throws IOException {
    TasksConfig tasksConfig = new TasksConfig();
    parse(filePath, tasksConfig);
    return tasksConfig.tasks;
  }

  private void task(Closure<?> closure) {
    TaskDTO taskDTO = new TaskDTO();
    delegate(taskDTO, closure);
    tasks.add(new Task(taskDTO.name, taskDTO.description));
  }

  private static class TaskDTO {
    private String name;
    private String description;
  }

}
