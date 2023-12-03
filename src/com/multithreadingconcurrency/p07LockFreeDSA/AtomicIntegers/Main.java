package com.multithreadingconcurrency.p07LockFreeDSA.AtomicIntegers;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    AtomicInventoryCounter inventoryCounter = new AtomicInventoryCounter();
    DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);
    IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);

    incrementingThread.start();
    decrementingThread.start();

    incrementingThread.join();
    decrementingThread.join();

    System.out.println(inventoryCounter.getCounter());
  }
}
