package ru.nsu.fit.oop.melnikov.dsl.git;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import ru.nsu.fit.oop.melnikov.dsl.GlobalConstants;

public class ReposDownloader {
  private static final String VCS_URL = "https://github.com";
  private static final String DEFAULT_DIR_NAME = "oop";
  private static final String POSTFIX = ".git";

  private ReposDownloader() {}

  public static Git download(String studentGitName) throws GitAPIException, IOException {
    File workingDirectory = new File(GlobalConstants.REPOS_DIR_PATH + '/' + studentGitName);
    if (workingDirectory.exists()) {
      return Git.open(workingDirectory);
    }
    return Git.cloneRepository()
        .setURI(VCS_URL + '/' + studentGitName + '/' + DEFAULT_DIR_NAME + POSTFIX)
        .setDirectory(workingDirectory)
        .call();
  }

  public static Collection<Git> downloadAll(Collection<String> studentGitNames)
      throws GitAPIException, IOException {
    Collection<Git> studentGits = new ArrayList<>(studentGitNames.size());
    for (String studentGitName : studentGitNames) {
      studentGits.add(download(studentGitName));
    }
    return studentGits;
  }
}
