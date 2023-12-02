package com.multithreadingconcurrency.p05Synchronizers.Semaphore;

/*
 * Practical Example: Database Connection Pool
 * 
 * In this example, we'll simulate a database connection pool using a semaphore.
 * The pool size is limited (5), and each thread (10) must acquire a permit from
 * the semaphore before it can access a connection.
 * After using the connection, the thread releases the permit.
 */

public class SemaphoreDemo {
  public static void main(String[] args) {
    DBConnectionPool pool = new DBConnectionPool(5);
    for (int i = 0; i < 10; i++) {
      int threadId = i;
      new Thread(() -> {
        try {
          pool.useConnection(threadId);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    }
  }
}