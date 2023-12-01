package com.multithreadingconcurrency.p02ThreadCoordination.DaemonThread;

import java.math.BigInteger;

import com.multithreadingconcurrency.p02ThreadCoordination.PowerCalculatingThread;

public class DaemonThread {
  public static void main(String[] args) throws InterruptedException {
    PowerCalculatingThread pct = new PowerCalculatingThread(BigInteger.valueOf(12345L),
        BigInteger.valueOf(54321L));
    pct.setDaemon(true);
    pct.start();
    Thread.sleep(1200);
  }
}
