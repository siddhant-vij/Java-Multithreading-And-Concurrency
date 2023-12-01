package com.multithreadingconcurrency.p03CriticalSection.MinMaxMetrics;

public class MinMaxMetrics {
  private volatile long minMetric;
  private volatile long maxMetric;

  public MinMaxMetrics() {
    this.minMetric = Long.MAX_VALUE;
    this.maxMetric = Long.MIN_VALUE;
  }

  public void addMetric(long newMetric) {
    synchronized (this) {
      this.minMetric = Math.min(newMetric, this.minMetric);
      this.maxMetric = Math.max(newMetric, this.maxMetric);
    }
  }

  public long getMin() {
    return this.minMetric;
  }

  public long getMax() {
    return this.maxMetric;
  }
}
