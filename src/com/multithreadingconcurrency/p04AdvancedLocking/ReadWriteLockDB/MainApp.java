package com.multithreadingconcurrency.p04AdvancedLocking.ReadWriteLockDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainApp {
  public static final int HIGHEST_PRICE = 1000;

  public static void main(String[] args) throws InterruptedException {
    initWithReentrantLocks();
    System.out.println("--------------------");
    initWithReadWriteLocks();
    /*
     * Reading took 15851 ms
     * --------------------
     * Reading took 3232 ms
     */
  }

  public static void initWithReentrantLocks() throws InterruptedException {
    InventoryDB inventoryDB = new InventoryDB();

    Random random = new Random();
    for (int i = 0; i < 10000; i++) {
      inventoryDB.addProduct(random.nextDouble(HIGHEST_PRICE));
    }

    Thread writer = new Thread(() -> {
      while (true) {
        inventoryDB.addProduct(random.nextDouble(HIGHEST_PRICE));
        inventoryDB.removeProduct(random.nextDouble(HIGHEST_PRICE));
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    writer.setDaemon(true);
    writer.start();

    int numberOfReaders = 5;
    List<Thread> readers = new ArrayList<>();
    for (int readerIndex = 0; readerIndex < numberOfReaders; readerIndex++) {
      Thread reader = new Thread(() -> {
        for (int i = 0; i < 10000; i++) {
          double upperBoundPrice = random.nextDouble(HIGHEST_PRICE);
          double lowerBoundPrice = upperBoundPrice > 0 ? random.nextDouble(upperBoundPrice) : 0;
          inventoryDB.getProductCountInPriceRange(lowerBoundPrice, upperBoundPrice);
        }
      });
      reader.setDaemon(true);
      readers.add(reader);
    }

    long startReadingTime = System.currentTimeMillis();
    for (Thread reader : readers) {
      reader.start();
    }
    for (Thread reader : readers) {
      reader.join();
    }
    long endReadingTime = System.currentTimeMillis();
    System.out.println(String.format("Reading took %d ms", endReadingTime - startReadingTime));
  }

  public static void initWithReadWriteLocks() throws InterruptedException {
    InventoryDB_RWLock inventoryDB = new InventoryDB_RWLock();

    Random random = new Random();
    for (int i = 0; i < 10000; i++) {
      inventoryDB.addProduct(random.nextDouble(HIGHEST_PRICE));
    }

    Thread writer = new Thread(() -> {
      while (true) {
        inventoryDB.addProduct(random.nextDouble(HIGHEST_PRICE));
        inventoryDB.removeProduct(random.nextDouble(HIGHEST_PRICE));
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    writer.setDaemon(true);
    writer.start();

    int numberOfReaders = 5;
    List<Thread> readers = new ArrayList<>();
    for (int readerIndex = 0; readerIndex < numberOfReaders; readerIndex++) {
      Thread reader = new Thread(() -> {
        for (int i = 0; i < 10000; i++) {
          double upperBoundPrice = random.nextDouble(HIGHEST_PRICE);
          double lowerBoundPrice = upperBoundPrice > 0 ? random.nextDouble(upperBoundPrice) : 0;
          inventoryDB.getProductCountInPriceRange(lowerBoundPrice, upperBoundPrice);
        }
      });
      reader.setDaemon(true);
      readers.add(reader);
    }

    long startReadingTime = System.currentTimeMillis();
    for (Thread reader : readers) {
      reader.start();
    }
    for (Thread reader : readers) {
      reader.join();
    }
    long endReadingTime = System.currentTimeMillis();
    System.out.println(String.format("Reading took %d ms", endReadingTime - startReadingTime));
  }
}
