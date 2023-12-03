package com.multithreadingconcurrency.p06InterThreadComms;

/*
 * Step 1: Run MatricesGenerator.java
 * Step 2: Run MainApp.java
 */

import com.multithreadingconcurrency.p06InterThreadComms.utils.MatricesMultiplierConsumer;
import com.multithreadingconcurrency.p06InterThreadComms.utils.MatricesReaderProducer;
import com.multithreadingconcurrency.p06InterThreadComms.utils.QueueLockCondition;
import com.multithreadingconcurrency.p06InterThreadComms.utils.QueueWaitNotify;
import com.multithreadingconcurrency.p06InterThreadComms.utils.QueueWithoutHandler;
import com.multithreadingconcurrency.p06InterThreadComms.utils.ThreadSafeQueue;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

/*
 * This can be greatly optimized by using multithreaded approach to
 * matrix generation & multiplication - using Virtual Threads.
 * 
 * Refer: https://github.com/siddhant-vij/Java-Projects/blob/main/src/p0Projects/ConcurrentMatrixMultiply.java
 */

public class MainApp {
  private final static String INPUT_FILE = "src\\com\\multithreadingconcurrency\\p06InterThreadComms\\data\\MatricesDatabase.txt";
  private final static int N = 10;

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Sample Test for NUM_MATRIXES = " + MatricesGenerator.getNumMatrices());

    System.out.println("--------------------");

    ThreadSafeQueue queue1 = new QueueWithoutHandler();
    testQueueImplementation(queue1, outputFileName(queue1));

    System.out.println("--------------------");
    ThreadSafeQueue queue2 = new QueueWaitNotify();
    testQueueImplementation(queue2, outputFileName(queue2));

    System.out.println("--------------------");
    ThreadSafeQueue queue3 = new QueueLockCondition();
    testQueueImplementation(queue3, outputFileName(queue3));
  }

  private static String outputFileName(ThreadSafeQueue queue) {
    return "src\\com\\multithreadingconcurrency\\p06InterThreadComms\\out\\Result_"
        + queue.getClass().getSimpleName().substring(5)
        + ".txt";
  }

  private static void testQueueImplementation(ThreadSafeQueue queue, String outputFile) throws FileNotFoundException {
    long startTime = System.currentTimeMillis();

    MatricesReaderProducer reader = new MatricesReaderProducer(queue, new BufferedReader(new FileReader(INPUT_FILE)),
        N);
    MatricesMultiplierConsumer writer = new MatricesMultiplierConsumer(queue, new PrintWriter(
        new FileOutputStream(outputFile, true)));

    Thread producerThread = new Thread(reader);
    Thread consumerThread = new Thread(writer);

    producerThread.start();
    consumerThread.start();

    try {
      producerThread.join();
      consumerThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    System.out.println("Time taken for " + queue.getClass().getSimpleName() + ": " + duration + " ms");
  }
}

/*
 * Sample Test for NUM_MATRIXES = 10
 * --------------------
 * Time taken for QueueWithoutHandler: 63 ms
 * --------------------
 * Time taken for QueueWaitNotify: 12 ms
 * --------------------
 * Time taken for QueueLockCondition: 7 ms
 */

/*
 * Sample Test for NUM_MATRIXES = 100000
 * --------------------
 * Time taken for QueueWithoutHandler: 3326 ms
 * --------------------
 * Time taken for QueueWaitNotify: 2726 ms
 * --------------------
 * Time taken for QueueLockCondition: 2679 ms
 */

/*
 * Caveats for Memory Usage Estimation
 * - This approach provides an estimate of memory usage, but it's not precise.
 * The JVM's garbage collector might run at any time, affecting the memory
 * usage.
 * 
 * Force Garbage Collection Before Measurement, but even that's just a
 * suggestion to the JVM, and actual GC is still at the JVM's discretion.
 * 
 * VisualVM can offer insights into memory allocation & garbage collection.
 */
