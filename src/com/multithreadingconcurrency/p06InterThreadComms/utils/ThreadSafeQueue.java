package com.multithreadingconcurrency.p06InterThreadComms.utils;

public interface ThreadSafeQueue {
  public void add(MatricesPair matricesPair);
  public MatricesPair remove();
  public void terminate();
}
