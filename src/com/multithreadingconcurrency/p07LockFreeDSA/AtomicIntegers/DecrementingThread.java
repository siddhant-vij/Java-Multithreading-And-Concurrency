package com.multithreadingconcurrency.p07LockFreeDSA.AtomicIntegers;

public class DecrementingThread extends Thread {
  private AtomicInventoryCounter inventoryCounter;

  public DecrementingThread(AtomicInventoryCounter inventoryCounter) {
    this.inventoryCounter = inventoryCounter;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10000; i++) {
      inventoryCounter.decrement();
    }
  }
}
