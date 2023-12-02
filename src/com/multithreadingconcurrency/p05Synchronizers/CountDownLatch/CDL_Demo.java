package com.multithreadingconcurrency.p05Synchronizers.CountDownLatch;

/*
 * Practical Example: Service Initialization
 * 
 * In this example, we simulate a scenario where a server should only start
 * after multiple services have been initialized.
 * Each service initialization is represented by a separate thread, and the
 * server start-up waits for all these services to complete their initialization.
 */

import java.util.List;

public class CDL_Demo {
  public static void main(String[] args) throws InterruptedException {
    ServiceInitializer initializer = new ServiceInitializer(5);

    List<String> services = List.of(
        "Database",
        "Cache",
        "Configuration",
        "Network",
        "File System");

    for (String service : services) {
      initializer.startService(service + " Service");
    }

    initializer.startServer();
  }
}
