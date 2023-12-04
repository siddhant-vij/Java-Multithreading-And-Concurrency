package com.multithreadingconcurrency.p08Performance.ThreadingModels.NonBlockingIOAsync;

import java.util.concurrent.CompletableFuture;

import com.multithreadingconcurrency.p08Performance.ThreadingModels.LongTimeTakingIO;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.ThreadingModel;

public class NonBlockingIOAsyncDemo implements ThreadingModel {
  private static final int NUMBER_OF_TASKS = 1000;
  private LongTimeTakingIO obj;

  public NonBlockingIOAsyncDemo(LongTimeTakingIO obj) {
    this.obj = obj;
  }

  @Override
  public void performTasks() {
    CompletableFuture<?>[] futures = new CompletableFuture[NUMBER_OF_TASKS];
    long start = System.currentTimeMillis();

    for (int i = 0; i < NUMBER_OF_TASKS; i++) {
      futures[i] = CompletableFuture.runAsync(() -> {
        obj.startOperation();
        obj.longTimeTakingIoOperation();
        obj.endOperation();
      });
    }

    CompletableFuture.allOf(futures).join();
    long end = System.currentTimeMillis();
    System.out.println(getClass().getSimpleName() + " completed in " + (end - start) + " ms");
  }
}
