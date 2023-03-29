package ru.nsu.fit.oop.melnikov.pizzeria.warehouse.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Interrupts specified in constructor thread when time is up.
 */
public class WaitingTimer extends Timer {

  private final Thread threadToInterrupt;
  private volatile boolean isTimerTriggered = false;

  public WaitingTimer(Thread threadToInterrupt) {
    this.threadToInterrupt = threadToInterrupt;
  }

  public boolean isTimerTriggered() {
    return isTimerTriggered;
  }

  /**
   * Schedules for executing WaitingTimerTask on timer trigger.
   *
   * @param delay delay for timer in milliseconds
   */
  public void start(long delay) {
    super.schedule(new WaitingTimerTask(), delay);
  }

  /**
   * Contains task that will be executed on timer trigger. It sets isTimerTriggered flag and
   * interrupts a thread.
   */
  private class WaitingTimerTask extends TimerTask {

    @Override
    public void run() {
      WaitingTimer.this.isTimerTriggered = true;
      WaitingTimer.this.threadToInterrupt.interrupt();
    }
  }
}
