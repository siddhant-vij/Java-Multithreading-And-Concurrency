package com.multithreadingconcurrency.p05InterThreadComms.Phaser;

import java.util.concurrent.Phaser;

public class MultiPhaseTaskProcessor implements Runnable {
  private int id;
  private Phaser phaser;

  MultiPhaseTaskProcessor(int id, Phaser phaser) {
    this.id = id;
    this.phaser = phaser;
    this.phaser.register();
  }

  @Override
  public void run() {
    while (!phaser.isTerminated()) {
      System.out.println("Task: " + id + " started with Phase " + phaser.getPhase());
      phaser.arriveAndAwaitAdvance();
      try {
        Thread.sleep((int) (Math.random() * 1000)); // Simulating task execution
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
