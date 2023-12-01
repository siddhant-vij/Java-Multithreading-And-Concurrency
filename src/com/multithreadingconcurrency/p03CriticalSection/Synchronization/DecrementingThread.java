package com.multithreadingconcurrency.p03CriticalSection.Synchronization;

public class DecrementingThread extends Thread {
  private SyncInventoryCounter inventoryCounter;

  public DecrementingThread(SyncInventoryCounter inventoryCounter) {
    this.inventoryCounter = inventoryCounter;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      inventoryCounter.decrement();
    }
  }
}
