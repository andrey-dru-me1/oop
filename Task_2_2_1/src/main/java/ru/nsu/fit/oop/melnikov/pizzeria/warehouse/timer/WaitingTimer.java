package ru.nsu.fit.oop.melnikov.pizzeria.warehouse.timer;

import java.util.Timer;
import java.util.TimerTask;

public class WaitingTimer extends Timer {

  private final Thread threadToInterrupt;
  private volatile boolean isTimerTriggered = false;

  public WaitingTimer(Thread threadToInterrupt) {
    this.threadToInterrupt = threadToInterrupt;
  }

  public boolean isTimerTriggered() {
    return isTimerTriggered;
  }

  public void start(long delay) {
    super.schedule(new WaitingTimerTask(), delay);
  }

  private class WaitingTimerTask extends TimerTask {

    @Override
    public void run() {
      WaitingTimer.this.isTimerTriggered = true;
      WaitingTimer.this.threadToInterrupt.interrupt();
    }
  }
}
