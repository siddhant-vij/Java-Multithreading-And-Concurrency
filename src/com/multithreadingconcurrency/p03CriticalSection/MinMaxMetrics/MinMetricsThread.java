package com.multithreadingconcurrency.p03CriticalSection.MinMaxMetrics;

public class MinMetricsThread extends Thread {
  private MinMaxMetrics minMaxMetrics;

  public MinMetricsThread(MinMaxMetrics minMaxMetrics) {
    this.minMaxMetrics = minMaxMetrics;
  }

  @Override
  public void run() {
    while (true) {
      System.out.println("Minimum metric: " + minMaxMetrics.getMin());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
