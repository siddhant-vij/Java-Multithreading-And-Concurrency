package com.multithreadingconcurrency.p04AdvancedLocking.ReentrantLockUI;

public class PriceUpdater extends Thread {
  private PricesContainer pricesContainer;

  public PriceUpdater(PricesContainer pricesContainer) {
    this.pricesContainer = pricesContainer;
  }

  @Override
  public void run() {
    while (true) {
      pricesContainer.getLockObject().lock();
      try {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        pricesContainer.setStock1Price(Math.random() * 10);
        System.out.println("Stock 1: " + pricesContainer.getStock1Price());
        pricesContainer.setStock2Price(Math.random() * 100);
        System.out.println("Stock 2: " + pricesContainer.getStock2Price());
        pricesContainer.setStock3Price(Math.random() * 1000);
        System.out.println("Stock 3: " + pricesContainer.getStock3Price());
        pricesContainer.setStock4Price(Math.random() * 100);
        System.out.println("Stock 4: " + pricesContainer.getStock4Price());
        pricesContainer.setStock5Price(Math.random() * 10);
        System.out.println("Stock 5: " + pricesContainer.getStock5Price());
      } finally {
        pricesContainer.getLockObject().unlock();
      }
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
