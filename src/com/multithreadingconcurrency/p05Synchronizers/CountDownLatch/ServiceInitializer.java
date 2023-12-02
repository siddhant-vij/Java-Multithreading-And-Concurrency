package com.multithreadingconcurrency.p05Synchronizers.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class ServiceInitializer {
  private final CountDownLatch latch;

  public ServiceInitializer(int numberOfServices) {
    this.latch = new CountDownLatch(numberOfServices);
  }

  public void startService(String serviceName) {
    new Thread(() -> {
      try {
        System.out.println(serviceName + " is initializing.");
        int initTime = (int) (Math.random() * 1000);
        Thread.sleep(initTime); // Simulating the service initialization
        System.out.println("After " + initTime + "ms, " + serviceName + " has been initialized.");
        latch.countDown();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }).start();
  }

  public void startServer() throws InterruptedException {
    latch.await();
    System.out.println("\nAll services initialized. Server is starting.");
  }
}