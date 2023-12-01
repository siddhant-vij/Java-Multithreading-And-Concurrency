package com.multithreadingconcurrency.p01ThreadCreation.PoliceHackerThreads;

public class Police extends Thread {
  @Override
  public void run() {
    System.out.println("Starting " + this.getClass().getSimpleName() + " thread");
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(i + 1);
    }
    System.out.println("Game over for hackers!");
    System.exit(0);
  }
}
