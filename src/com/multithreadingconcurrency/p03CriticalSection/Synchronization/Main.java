package com.multithreadingconcurrency.p03CriticalSection.Synchronization;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    SyncInventoryCounter inventoryCounter = new SyncInventoryCounter();
    DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);
    IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);

    incrementingThread.start();
    decrementingThread.start();

    incrementingThread.join();
    decrementingThread.join();

    System.out.println(inventoryCounter.getCounter());
  }
}
