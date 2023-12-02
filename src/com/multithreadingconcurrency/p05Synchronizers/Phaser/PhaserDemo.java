package com.multithreadingconcurrency.p05Synchronizers.Phaser;

/*
 * Practical Example: Multi-phase Task Processing
 * 
 * Let's consider an example where tasks are processed in multiple
 * phases, and the number of tasks in each phase can vary.
 * This is a typical scenario where Phaser is useful.
 */

public class PhaserDemo {
  public static void main(String[] args) {
    CustomPhaser phaser = new CustomPhaser(1, 3);
    for (int i = 0; i < 3; i++) {
      new Thread(new MultiPhaseTaskProcessor(i, phaser)).start();
    }
    while (!phaser.isTerminated()) {
      phaser.arriveAndAwaitAdvance();
    }
    System.out.println("Exiting...");
  }
}
