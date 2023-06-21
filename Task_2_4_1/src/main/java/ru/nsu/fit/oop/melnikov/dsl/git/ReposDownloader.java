package ru.nsu.fit.oop.melnikov.dsl.git;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.fit.oop.melnikov.dsl.GlobalConstants;
import ru.nsu.fit.oop.melnikov.dsl.model.Student;

public class ReposDownloader {
  private static final String VCS_URL = "https://github.com";
  private static final String DEFAULT_DIR_NAME = "oop";
  private static final String POSTFIX = ".git";

  private ReposDownloader() {}

  public static void downloadAndBind(Student student) throws GitAPIException, IOException {
    File workingDirectory = new File(GlobalConstants.REPOS_DIR_PATH + '/' + student.gitName());
    if (workingDirectory.exists()) {
      try (Git git = Git.open(workingDirectory)) {
        git.pull().call();
        student.setGit(git);
      }
    } else {
      student.setGit(
          Git.cloneRepository()
              .setURI(VCS_URL + '/' + student.gitName() + '/' + DEFAULT_DIR_NAME + POSTFIX)
              .setDirectory(workingDirectory)
              .call());
    }
  }

  public static void downloadAndBindAll(Collection<Student> students)
      throws GitAPIException, IOException {
    for (Student student : students) {
      downloadAndBind(student);
    }
  }
}
