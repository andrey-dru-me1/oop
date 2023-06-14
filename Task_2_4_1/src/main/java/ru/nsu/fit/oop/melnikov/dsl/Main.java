package ru.nsu.fit.oop.melnikov.dsl;

import groovy.lang.*;
import groovy.util.DelegatingScript;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import org.codehaus.groovy.control.CompilerConfiguration;

public class Main {

  public static void main(String[] args) throws IOException {

    CompilerConfiguration cc = new CompilerConfiguration();
    cc.setScriptBaseClass(
        DelegatingScript.class
            .getName()); // благодаря этой настройке все создаваемые groovy скрипты будут
    // наследоваться от DelegatingScript
    GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);
    DelegatingScript script = (DelegatingScript) sh.parse(new File("dsl_configs/group.groovy"));
    Group group = new Group();
    script.setDelegate(group);
    script.run();
    System.out.println(group);
  }

  private static class Group extends ConfigObject {
    private Integer number;
    private Collection<Student> students = new ArrayList<>();

    public void student(Closure<?> closure) {
      Student student = new Student();
      closure.setDelegate(student);
      closure.setResolveStrategy(Closure.DELEGATE_FIRST);
      closure.call();
      students.add(student);
    }

    @Override
    public String toString() {
      return "Group{" + "number=" + number + ", students=" + students + '}';
    }
  }

  private static class ConfigObject extends GroovyObjectSupport {
    public void methodMissing(String name, Object args)
        throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
      MetaProperty metaProperty = getMetaClass().getMetaProperty(name);

      if (metaProperty == null) throw new IllegalArgumentException("No such field: " + name);

      if (args instanceof Object[] argArray && argArray[0] instanceof Closure<?> closure) {
        Object value = getProperty(name);
        if (value == null) {
          Class<?> clazz = metaProperty.getType();
          value = clazz.getConstructor().newInstance();
        }
        closure.setDelegate(value);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
        setProperty(name, value);
      }
    }
  }

  private static class Student {
    private String name;

    public Student() {}

    @Override
    public String toString() {
      return "Student{" + "name='" + name + '\'' + '}';
    }
  }
}
