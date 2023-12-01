package com.multithreadingconcurrency.p03CriticalSection.DeadlockDemo;

public class FirstTrain extends Thread {
  private Intersection intersection;

  public FirstTrain(Intersection intersection) {
    this.intersection = intersection;
  }

  @Override
  public void run() {
    while (true) {
      intersection.takeFirstRoad();
    }
  }
}
