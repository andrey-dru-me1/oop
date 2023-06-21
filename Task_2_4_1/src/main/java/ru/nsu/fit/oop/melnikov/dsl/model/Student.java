package ru.nsu.fit.oop.melnikov.dsl.model;

import java.util.Objects;
import org.eclipse.jgit.api.Git;

public final class Student {
  private final String name;
  private final String gitName;
  private Git git;

  public Student(String name, String gitName) {
    this.name = name;
    this.gitName = gitName;
  }

  public String name() {
    return name;
  }

  public String gitName() {
    return gitName;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Student) obj;
    return Objects.equals(this.name, that.name) && Objects.equals(this.gitName, that.gitName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, gitName);
  }

  @Override
  public String toString() {
    return "Student[" + "name=" + name + ", " + "gitName=" + gitName + ']';
  }

  public Git getGit() {
    return git;
  }

  public void setGit(Git git) {
    this.git = git;
  }
}
