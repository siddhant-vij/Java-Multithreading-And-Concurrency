package com.multithreadingconcurrency.p03CriticalSection.MinMaxMetrics;

public class MaxMetricsThread extends Thread {
  private MinMaxMetrics minMaxMetrics;

  public MaxMetricsThread(MinMaxMetrics minMaxMetrics) {
    this.minMaxMetrics = minMaxMetrics;
  }

  @Override
  public void run() {
    while (true) {
      System.out.println("Maximum metric: " + minMaxMetrics.getMax());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
