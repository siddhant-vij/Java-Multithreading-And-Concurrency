package com.multithreadingconcurrency.p07LockFreeDSA.AtomicReferences;

public class StandardStack<T> implements Stack<T> {
  private StackNode<T> top;
  private int counter;

  public StandardStack() {
    top = null;
    counter = 0;
  }

  @Override
  public synchronized void push(T data) {
    StackNode<T> node = new StackNode<>(data);
    node.setNext(top);
    top = node;
    counter++;
  }

  @Override
  public synchronized T pop() {
    if (top == null) {
      counter++;
      return null;
    } else {
      T data = top.getData();
      top = top.getNext();
      counter++;
      return data;
    }
  }

  @Override
  public int getCounter() {
    return counter;
  }
}
