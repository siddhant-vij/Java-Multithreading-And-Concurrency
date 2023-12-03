package com.multithreadingconcurrency.p06InterThreadComms.utils;

import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class QueueLockCondition implements ThreadSafeQueue {
  private Queue<MatricesPair> queue = new LinkedList<>();
  private boolean isEmpty = true;
  private boolean isTerminate = false;
  private static final int CAPACITY = 5;
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  @Override
  public void add(MatricesPair matricesPair) {
    lock.lock();
    try {
      while (queue.size() == CAPACITY) {
        try {
          condition.await();
        } catch (InterruptedException e) {
        }
      }
      queue.add(matricesPair);
      isEmpty = false;
      // System.out.println("Added a pair. Queue size: " + queue.size());
      condition.signal();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public MatricesPair remove() {
    lock.lock();
    try {
      while (isEmpty && !isTerminate) {
        try {
          condition.await();
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
      MatricesPair matricesPair = queue.remove();
      if (queue.size() == CAPACITY - 1) {
        condition.signalAll();
      }
      return matricesPair;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void terminate() {
    lock.lock();
    try {
      isTerminate = true;
      condition.signalAll();
    } finally {
      lock.unlock();
    }
  }
}