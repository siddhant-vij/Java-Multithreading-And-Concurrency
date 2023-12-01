package com.multithreadingconcurrency.p04AdvancedLocking.ReadWriteLockDB;

import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InventoryDB_RWLock {
  private TreeMap<Double, Integer> priceToCountMap = new TreeMap<>();
  private ReentrantReadWriteLock lockObject = new ReentrantReadWriteLock();

  public int getProductCountInPriceRange(double lPrice, double hPrice) {
    lockObject.readLock().lock();
    try {
      int count = 0;
      for (double price : priceToCountMap.keySet()) {
        if (price >= lPrice && price <= hPrice) {
          count += priceToCountMap.get(price);
        }
      }
      return count;
    } finally {
      lockObject.readLock().unlock();
    }
  }

  public void addProduct(double price) {
    lockObject.writeLock().lock();
    try {
      priceToCountMap.put(price, priceToCountMap.getOrDefault(price, 0) + 1);
    } finally {
      lockObject.writeLock().unlock();
    }
  }

  public void removeProduct(double price) {
    lockObject.writeLock().lock();
    try {
      Integer count = priceToCountMap.get(price);
      if (count == null || count == 1) {
        priceToCountMap.remove(price);
      } else {
        priceToCountMap.put(price, count - 1);
      }
    } finally {
      lockObject.writeLock().unlock();
    }
  }
}
