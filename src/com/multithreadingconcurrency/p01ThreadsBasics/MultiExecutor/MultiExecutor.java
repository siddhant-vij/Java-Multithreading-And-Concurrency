package com.multithreadingconcurrency.p01ThreadsBasics.MultiExecutor;

import java.util.List;

public class MultiExecutor {
  private List<Runnable> tasks;

  public MultiExecutor(List<Runnable> tasks) {
    this.tasks = tasks;
  }

  public void executeAll() {
    for (Runnable task : tasks) {
      new Thread(task).start();
    }
  }
}
