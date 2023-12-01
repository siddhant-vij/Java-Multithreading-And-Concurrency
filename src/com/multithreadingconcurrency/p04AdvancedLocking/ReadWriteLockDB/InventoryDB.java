package com.multithreadingconcurrency.p04AdvancedLocking.ReadWriteLockDB;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InventoryDB {
  private TreeMap<Double, Integer> priceToCountMap = new TreeMap<>();
  private Lock lockObject = new ReentrantLock();

  public int getProductCountInPriceRange(double lPrice, double hPrice) {
    lockObject.lock();
    try {
      int count = 0;
      for (double price : priceToCountMap.keySet()) {
        if (price >= lPrice && price <= hPrice) {
          count += priceToCountMap.get(price);
        }
      }
      return count;
    } finally {
      lockObject.unlock();
    }
  }

  public void addProduct(double price) {
    lockObject.lock();
    try {
      priceToCountMap.put(price, priceToCountMap.getOrDefault(price, 0) + 1);
    } finally {
      lockObject.unlock();
    }
  }

  public void removeProduct(double price) {
    lockObject.lock();
    try {
      Integer count = priceToCountMap.get(price);
      if (count == null || count == 1) {
        priceToCountMap.remove(price);
      } else {
        priceToCountMap.put(price, count - 1);
      }
    } finally {
      lockObject.unlock();
    }
  }
}
