package com.multithreadingconcurrency.p07LockFreeDSA.AtomicReferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PerformanceMain {
  public static void main(String[] args) throws InterruptedException {
    Stack<Integer> stack1 = new StandardStack<>();
    System.out.println("Standard Stack Performance:");
    performance(stack1);

    System.out.println("---------------------");

    Stack<Integer> stack2 = new LockFreeStack<>();
    System.out.println("Lock-free Stack Performance:");
    performance(stack2);

    /*
     * Standard Stack Performance:
     * 268,463,079 operations were performed in 10 seconds
     * ---------------------
     * Lock-free Stack Performance:
     * 405,159,132 operations were performed in 10 seconds
     */
  }

  private static void performance(Stack<Integer> stack) throws InterruptedException {
    Random random = new Random();

    for (int i = 0; i < 100000; i++) {
      stack.push(random.nextInt());
    }

    List<Thread> threads = new ArrayList<>();

    int pushingThreads = 2;
    int poppingThreads = 2;

    for (int i = 0; i < pushingThreads; i++) {
      Thread thread = new Thread(() -> {
        while (true) {
          stack.push(random.nextInt());
        }
      });

      thread.setDaemon(true);
      threads.add(thread);
    }

    for (int i = 0; i < poppingThreads; i++) {
      Thread thread = new Thread(() -> {
        while (true) {
          stack.pop();
        }
      });

      thread.setDaemon(true);
      threads.add(thread);
    }

    for (Thread thread : threads) {
      thread.start();
    }

    Thread.sleep(10000);

    System.out.println(String.format("%,d operations were performed in 10 seconds ", stack.getCounter()));
  }
}
