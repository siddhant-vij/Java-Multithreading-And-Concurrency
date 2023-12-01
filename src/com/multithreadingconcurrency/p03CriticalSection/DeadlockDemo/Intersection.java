package com.multithreadingconcurrency.p03CriticalSection.DeadlockDemo;

public class Intersection {
  private Object firstRoad = new Object();
  private Object secondRoad = new Object();

  public void takeFirstRoad() {
    synchronized (firstRoad) {
      System.out.println("First road is blocked by " + Thread.currentThread().getName());
      synchronized (secondRoad) {
        System.out.println("Train passing through first road");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void takeSecondRoad() {
    synchronized (secondRoad) {
      System.out.println("Second road is blocked by " + Thread.currentThread().getName());
      synchronized (firstRoad) {
        System.out.println("Train passing through second road");
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
