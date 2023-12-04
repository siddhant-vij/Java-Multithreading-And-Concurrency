package com.multithreadingconcurrency.p08Performance.ThreadingModels.IOVirtualThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.multithreadingconcurrency.p08Performance.ThreadingModels.LongTimeTakingIO;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.ThreadingModel;

public class IOVirtualThreadsDemo implements ThreadingModel {
  private static final int NUMBER_OF_TASKS = 1000;
  private LongTimeTakingIO obj;

  public IOVirtualThreadsDemo(LongTimeTakingIO obj) {
    this.obj = obj;
  }

  @Override
  public void performTasks() {
    ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();
    long start = System.currentTimeMillis();

    for (int i = 0; i < NUMBER_OF_TASKS; i++) {
      es.execute(() -> {
        obj.startOperation();
        obj.longTimeTakingIoOperation();
        obj.endOperation();
      });
    }

    es.shutdown();
    try {
      es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    long end = System.currentTimeMillis();
    System.out.println(getClass().getSimpleName() + " completed in " + (end - start) + " ms");
  }
}
