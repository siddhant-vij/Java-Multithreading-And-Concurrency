package com.multithreadingconcurrency.p03CriticalSection.DeadlockDemo;

public class SecondTrain extends Thread {
  private Intersection intersection;

  public SecondTrain(Intersection intersection) {
    this.intersection = intersection;
  }

  @Override
  public void run() {
    while (true) {
      intersection.takeSecondRoad();
    }
  }
}
