package com.multithreadingconcurrency.p05Synchronizers.Semaphore;

import java.util.concurrent.Semaphore;

public class DBConnectionPool {
  private final Semaphore semaphore;
  private final boolean[] connections;
  private final int MAX_CONNECTIONS;

  public DBConnectionPool(int maxConnections) {
    this.MAX_CONNECTIONS = maxConnections;
    this.semaphore = new Semaphore(maxConnections);
    this.connections = new boolean[maxConnections];
  }

  public void useConnection(int threadId) throws InterruptedException {
    semaphore.acquire();
    try {
      int connIndex = getConnection();
      System.out.println("Thread " + threadId + " using connection " + connIndex);
      Thread.sleep((int) (Math.random() * 1000)); // Simulating connection usage
      releaseConnection(connIndex);
    } finally {
      semaphore.release();
    }
  }

  private synchronized int getConnection() {
    for (int i = 0; i < MAX_CONNECTIONS; i++) {
      if (!connections[i]) {
        connections[i] = true;
        return i;
      }
    }
    return -1;
  }

  private synchronized void releaseConnection(int index) {
    connections[index] = false;
  }
}
