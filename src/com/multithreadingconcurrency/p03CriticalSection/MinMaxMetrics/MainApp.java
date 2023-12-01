package com.multithreadingconcurrency.p03CriticalSection.MinMaxMetrics;

public class MainApp {
  public static void main(String[] args) throws InterruptedException {
    MinMaxMetrics minMaxMetrics = new MinMaxMetrics();
    MinMetricsThread minMetricsThread = new MinMetricsThread(minMaxMetrics);
    MaxMetricsThread maxMetricsThread = new MaxMetricsThread(minMaxMetrics);
    BusinessLogicThread businessLogicThread = new BusinessLogicThread(minMaxMetrics);

    minMetricsThread.start();
    maxMetricsThread.start();
    businessLogicThread.start();

    minMetricsThread.join();
    maxMetricsThread.join();
    businessLogicThread.join();
  }
}
