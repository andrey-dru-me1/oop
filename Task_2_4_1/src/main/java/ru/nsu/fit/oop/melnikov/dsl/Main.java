package ru.nsu.fit.oop.melnikov.dsl;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import groovy.lang.MetaProperty;
import groovy.util.DelegatingScript;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.codehaus.groovy.control.CompilerConfiguration;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.getMetaClass;

public class Main {

  public static void main(String[] args) throws IOException {
    CompilerConfiguration cc = new CompilerConfiguration();
    cc.setScriptBaseClass(DelegatingScript.class.getName());
    GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);
    DelegatingScript script = (DelegatingScript) sh.parse(new File("dsl_configs/group.groovy"));
//    GroupsConfig config = new GroupsConfig();
    Group config = new Group();
    script.setDelegate(config);
    script.run();
    System.out.println(config);
  }

  private static class Group {
    private Integer number;
    private Collection<Student> students;

    public void methodMissing(String name, Object args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
      MetaProperty metaProperty = getMetaClass(this).getMetaProperty(name);
      if (metaProperty != null) {
        Closure<?> closure = (Closure<?>) ((Object[]) args)[0];
        Object value = getProperty(name) == null ?
                metaProperty.getType().getConstructor().newInstance() :
                getProperty(name);
        closure.setDelegate(value);
        closure.setResolveStrategy(Closure.DELEGATE_FIRST);
        closure.call();
        setProperty(name, (String) value);
      } else {
        throw new IllegalArgumentException("No such field: " + name);
      }
    }

    @Override
    public String toString() {
      return "Group{" +
              "number=" + number +
              ", students=" + students +
              '}';
    }
  }

  private static class Student {

    private String name;

    @Override
    public String toString() {
      return "Student{" +
              "name='" + name + '\'' +
              '}';
    }
  }

}
