package ru.nsu.fit.oop.melnikov.dsl.dto;

import groovy.lang.Closure;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;

public class GroupsConfig extends AbstractConfig {

  private final Collection<Group> groups;

  private GroupsConfig() {
    super();
    groups = new ArrayList<>();
  }

  public static Collection<Group> parse(String filePath) throws IOException {
    GroupsConfig groupsConfig = new GroupsConfig();
    parse(filePath, groupsConfig);
    return groupsConfig.groups;
  }

  private void group(Closure<?> closure) {
    GroupDTO groupDTO = new GroupDTO();
    delegate(groupDTO, closure);
    groups.add(new Group(groupDTO.number, groupDTO.students));
  }

  private static class GroupDTO {
    private final Collection<Student> students;
    private Integer number;

    public GroupDTO() {
      students = new ArrayList<>();
    }

    private void student(Closure<?> closure) {
      StudentDTO studentDTO = new StudentDTO();
      delegate(studentDTO, closure);
      students.add(new Student(studentDTO.name, studentDTO.gitName));
    }

    private static class StudentDTO {
      private String name;
      private String gitName;
    }
  }
}
