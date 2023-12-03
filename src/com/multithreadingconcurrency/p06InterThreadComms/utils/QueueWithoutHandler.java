package com.multithreadingconcurrency.p06InterThreadComms.utils;

import java.util.Queue;
import java.util.LinkedList;

public class QueueWithoutHandler implements ThreadSafeQueue {
  private Queue<MatricesPair> queue = new LinkedList<>();
  private boolean isEmpty = true;
  private boolean isTerminate = false;

  @Override
  public synchronized void add(MatricesPair matricesPair) {
    queue.add(matricesPair);
    isEmpty = false;
    // System.out.println("Added a pair. Queue size: " + queue.size());
    notify();
  }

  @Override
  public synchronized MatricesPair remove() {
    while (isEmpty && !isTerminate) {
      try {
        wait();
      } catch (InterruptedException e) {
      }
    }
    if (queue.size() == 1) {
      isEmpty = true;
    }
    if (queue.size() == 0 && isTerminate) {
      return null;
    }
    // System.out.println("Queue size: " + queue.size() + ". Removing a pair.");
    return queue.remove();
  }

  @Override
  public synchronized void terminate() {
    isTerminate = true;
    notifyAll();
  }
}