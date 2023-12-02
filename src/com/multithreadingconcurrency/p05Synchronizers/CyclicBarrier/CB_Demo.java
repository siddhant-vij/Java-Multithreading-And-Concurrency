package com.multithreadingconcurrency.p05Synchronizers.CyclicBarrier;

/*
 * Practical Example: Parallel Computation
 * 
 * Let's consider a scenario where a task that can be broken down into
 * subtasks, each executed by a separate thread.
 * After completing their individual subtasks, threads wait for each other to
 * reach a common barrier before moving to the next phase of the task.
 * This kind of situation is ideal for CyclicBarrier.
 */

public class CB_Demo {
  public static void main(String[] args) {
    int numberOfThreads = 4;
    ParallelTask task = new ParallelTask(numberOfThreads);

    for (int i = 0; i < numberOfThreads; i++) {
      final int threadId = i;
      new Thread(() -> task.performSubtask(threadId)).start();
    }
  }
}
