package com.multithreadingconcurrency.p05Synchronizers.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class ParallelTask {
  private final CyclicBarrier barrier;
  private final int totalParties;

  public ParallelTask(int totalParties) {
    this.totalParties = totalParties;
    this.barrier = new CyclicBarrier(totalParties, barrierAction());
  }

  public Runnable barrierAction() {
    return () -> {
      System.out.println(totalParties + " parties have reached the barrier. Proceeding to the next phase...");
    };
  }

  public void performSubtask(int threadId) {
    try {
      System.out.println("Thread " + threadId + " is performing its subtask.");
      Thread.sleep((int)(Math.random() * 1000)); // Simulating subtask completion
      System.out.println("Thread " + threadId + " has reached the barrier.");
      barrier.await();
      // ...
      // The next phase of the task can be executed here
      // ...
    } catch (Exception e) {
      Thread.currentThread().interrupt();
    }
  }
}
