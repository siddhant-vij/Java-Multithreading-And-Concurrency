package com.multithreadingconcurrency.p07LockFreeDSA.AtomicIntegers;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicInventoryCounter {
  private AtomicInteger counter = new AtomicInteger(0);

  public void increment() {
    counter.incrementAndGet();
  }

  public void decrement() {
    counter.decrementAndGet();
  }

  public int getCounter() {
    return counter.get();
  }
}
