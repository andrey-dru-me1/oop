package ru.nsu.fit.oop.melnikov.dsl.dto;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.codehaus.groovy.control.CompilerConfiguration;
import ru.nsu.fit.oop.melnikov.dsl.model.Group;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;

public class GroupsConfig {

  private final Collection<Group> groups;

  private GroupsConfig() {
    groups = new ArrayList<>();
  }

  public static Collection<Group> parse(String filePath) throws IOException {
    CompilerConfiguration cc = new CompilerConfiguration();
    cc.setScriptBaseClass(DelegatingScript.class.getName());
    GroovyShell sh = new GroovyShell(GroupsConfig.class.getClassLoader(), new Binding(), cc);
    DelegatingScript script = (DelegatingScript) sh.parse(new File(filePath));
    GroupsConfig groupsConfig = new GroupsConfig();
    script.setDelegate(groupsConfig);
    script.run();
    return groupsConfig.groups;
  }

  private void group(Closure<?> closure) {
    GroupDTO groupDTO = new GroupDTO();
    closure.setDelegate(groupDTO);
    closure.setResolveStrategy(Closure.DELEGATE_FIRST);
    closure.call();
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
      closure.setDelegate(studentDTO);
      closure.setResolveStrategy(Closure.DELEGATE_FIRST);
      closure.call();
      students.add(new Student(studentDTO.name, studentDTO.gitName));
    }

    private static class StudentDTO {
      private String name;
      private String gitName;
    }
  }
}
