package com.multithreadingconcurrency.p03CriticalSection.SharingResource;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    InventoryCounter inventoryCounter = new InventoryCounter();
    DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);
    IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);

    incrementingThread.start();
    decrementingThread.start();

    incrementingThread.join();
    decrementingThread.join();

    System.out.println(inventoryCounter.getCounter());
  }
}
