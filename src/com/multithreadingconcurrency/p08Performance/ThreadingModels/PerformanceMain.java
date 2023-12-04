package com.multithreadingconcurrency.p08Performance.ThreadingModels;

import com.multithreadingconcurrency.p08Performance.ThreadingModels.BlockingIO.BlockingIODemo;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.IOVirtualThreads.IOVirtualThreadsDemo;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.NonBlockingIOAsync.NonBlockingIOAsyncDemo;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.ThreadPerTask.ThreadPerTaskDemo;

/*
 * BlockingIODemo completed in 18196 ms
 * ThreadPerTaskDemo completed in 177 ms
 * NonBlockingIOAsyncDemo completed in 21818 ms
 * IOVirtualThreadsDemo completed in 131 ms
 */

public class PerformanceMain {
  public static void main(String[] args) {
    LongTimeTakingIO obj = new LongTimeTakingIO();
    ThreadingModel[] models = {
        new BlockingIODemo(obj),
        new ThreadPerTaskDemo(obj),
        new NonBlockingIOAsyncDemo(obj),
        new IOVirtualThreadsDemo(obj)
    };

    for (ThreadingModel model : models) {
      model.performTasks();
    }
  }
}
