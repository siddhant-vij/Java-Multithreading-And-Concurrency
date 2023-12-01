package com.multithreadingconcurrency.p03CriticalSection.SharingResource;

public class InventoryCounter {
  private int counter = 0;

  public void increment() {
    counter++;
  }

  public void decrement() {
    counter--;
  }

  public int getCounter() {
    return counter;
  }
}
