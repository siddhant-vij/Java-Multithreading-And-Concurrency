package com.multithreadingconcurrency.p08Performance.ThreadingModels;

import com.multithreadingconcurrency.p08Performance.ThreadingModels.BlockingIO.BlockingIODemo;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.ContextSwitches.ContextSwitchesDemo;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.IOVirtualThreads.IOVirtualThreadsDemo;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.NonBlockingIOAsync.NonBlockingIOAsyncDemo;
import com.multithreadingconcurrency.p08Performance.ThreadingModels.ThreadPerTask.ThreadPerTaskDemo;

/*
 * BlockingIODemo completed in 18234 ms
 * ThreadPerTaskDemo completed in 174 ms
 * NonBlockingIOAsyncDemo completed in 21850 ms
 * IOVirtualThreadsDemo completed in 128 ms
 * ContextSwitchesDemo completed in 21982 ms
 */

public class PerformanceMain {
  public static void main(String[] args) {
    LongTimeTakingIO obj = new LongTimeTakingIO();
    ThreadingModel[] models = {
        new BlockingIODemo(obj),
        new ThreadPerTaskDemo(obj),
        new NonBlockingIOAsyncDemo(obj),
        new IOVirtualThreadsDemo(obj),
        new ContextSwitchesDemo(obj)
    };

    for (ThreadingModel model : models) {
      model.performTasks();
    }
  }
}
