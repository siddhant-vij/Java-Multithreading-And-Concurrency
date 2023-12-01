package com.multithreadingconcurrency.p03CriticalSection.MinMaxMetrics;

public class BusinessLogicThread extends Thread {
  private MinMaxMetrics minMaxMetrics;

  public BusinessLogicThread(MinMaxMetrics minMaxMetrics) {
    this.minMaxMetrics = minMaxMetrics;
  }

  @Override
  public void run() {
    while (true) {
      minMaxMetrics.addMetric((int) (Math.random() * 10000));
      minMaxMetrics.addMetric((int) (Math.random() * -10000));
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
