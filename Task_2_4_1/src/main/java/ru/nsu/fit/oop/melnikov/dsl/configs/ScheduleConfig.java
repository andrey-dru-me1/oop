package ru.nsu.fit.oop.melnikov.dsl.configs;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import ru.nsu.fit.oop.melnikov.dsl.model.Lesson;

public class ScheduleConfig extends AbstractConfig {

  private final Collection<Lesson> lessons;

  private ScheduleConfig() {
    super();
    lessons = new ArrayList<>();
  }

  public static Collection<Lesson> parse(String filename) throws IOException {
    ScheduleConfig scheduleConfig = new ScheduleConfig();
    parse(filename, scheduleConfig);
    return scheduleConfig.lessons;
  }

  private void lesson(String dateString) {
    try {
      lessons.add(new Lesson(new SimpleDateFormat("dd.MM.yyyy").parse(dateString)));
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
