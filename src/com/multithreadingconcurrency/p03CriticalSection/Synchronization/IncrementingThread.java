package com.multithreadingconcurrency.p03CriticalSection.Synchronization;

public class IncrementingThread extends Thread {
  private SyncInventoryCounter inventoryCounter;

  public IncrementingThread(SyncInventoryCounter inventoryCounter) {
    this.inventoryCounter = inventoryCounter;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      inventoryCounter.increment();
    }
  }
}
