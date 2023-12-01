package com.multithreadingconcurrency.p03CriticalSection.Synchronization;

public class SyncInventoryCounter {
  private int counter = 0;

  public void increment() {
    synchronized (this) {
      counter++;
    }
  }

  public void decrement() {
    synchronized (this) {
      counter--;
    }
  }

  public int getCounter() {
    return counter;
  }
}
