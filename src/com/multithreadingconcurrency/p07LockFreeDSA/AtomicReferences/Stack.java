package com.multithreadingconcurrency.p07LockFreeDSA.AtomicReferences;

public interface Stack<T> {
  void push(T data);
  T pop();
  int getCounter();
}
