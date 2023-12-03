package com.multithreadingconcurrency.p07LockFreeDSA.AtomicReferences;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class LockFreeStack<T> implements Stack<T> {
  private AtomicReference<StackNode<T>> top;
  private AtomicInteger counter;

  public LockFreeStack() {
    top = new AtomicReference<>();
    counter = new AtomicInteger(0);
  }

  @Override
  public void push(T data) {
    StackNode<T> node = new StackNode<>(data);
    while (true) {
      StackNode<T> current = top.get();
      node.setNext(current);
      if (top.compareAndSet(current, node)) {
        break;
      } else {
        LockSupport.parkNanos(1);
      }
    }
    counter.incrementAndGet();
  }

  @Override
  public T pop() {
    StackNode<T> current = top.get();
    StackNode<T> node;
    while (current != null) {
      node = current.getNext();
      if (top.compareAndSet(current, node)) {
        break;
      } else {
        LockSupport.parkNanos(1);
        current = top.get();
      }
    }
    counter.incrementAndGet();
    return current != null ? current.getData() : null;
  }

  @Override
  public int getCounter() {
    return counter.get();
  }
}
