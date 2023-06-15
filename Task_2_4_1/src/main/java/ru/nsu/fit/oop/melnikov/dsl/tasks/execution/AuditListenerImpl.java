package ru.nsu.fit.oop.melnikov.dsl.tasks.execution;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;

public class AuditListenerImpl implements AuditListener {

  private int errorCount;
  private int exceptionCount;

  public AuditListenerImpl() {
    errorCount = 0;
    exceptionCount = 0;
  }

  public int getErrorCount() {
    return errorCount;
  }

  public int getExceptionCount() {
    return exceptionCount;
  }

  @Override
  public void auditStarted(AuditEvent event) {}

  @Override
  public void auditFinished(AuditEvent event) {}

  @Override
  public void fileStarted(AuditEvent event) {}

  @Override
  public void fileFinished(AuditEvent event) {}

  @Override
  public void addError(AuditEvent event) {
    errorCount++;
  }

  @Override
  public void addException(AuditEvent event, Throwable throwable) {
    exceptionCount++;
  }
}
