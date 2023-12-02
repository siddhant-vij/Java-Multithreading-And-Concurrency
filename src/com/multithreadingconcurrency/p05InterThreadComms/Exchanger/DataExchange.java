package com.multithreadingconcurrency.p05InterThreadComms.Exchanger;

import java.util.concurrent.Exchanger;

public class DataExchange {
  private final Exchanger<String> exchanger = new Exchanger<>();

  public void startExchange() {
    new Thread(this::produce).start();
    new Thread(this::consume).start();
  }

  private void produce() {
    try {
      int i = 0;
      while (true) {
        String data = "Data-" + i++;
        System.out.println("Producing: " + data);
        // Exchanging data with the consumer
        data = exchanger.exchange(data);
        Thread.sleep((int) (Math.random() * 1000)); // Simulating time taken to produce data
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void consume() {
    try {
      String data;
      while (true) {
        // Receiving data from the producer
        data = exchanger.exchange(null);
        System.out.println("Consuming: " + data);
        Thread.sleep((int) (Math.random() * 1000)); // Simulating time taken to consume data
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}