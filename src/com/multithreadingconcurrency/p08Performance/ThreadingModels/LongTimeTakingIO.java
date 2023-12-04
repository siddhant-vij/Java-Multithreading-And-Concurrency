package com.multithreadingconcurrency.p08Performance.ThreadingModels;

public class LongTimeTakingIO {
  public void longTimeTakingIoOperation() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void startOperation() {
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void endOperation() {
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
