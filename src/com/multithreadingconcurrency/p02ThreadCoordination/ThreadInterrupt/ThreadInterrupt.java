package com.multithreadingconcurrency.p02ThreadCoordination.ThreadInterrupt;

import java.math.BigInteger;

import com.multithreadingconcurrency.p02ThreadCoordination.PowerCalculatingThread;

public class ThreadInterrupt {
  public static void main(String[] args) throws InterruptedException {
    PowerCalculatingThread pct = new PowerCalculatingThread(BigInteger.valueOf(12345L),
        BigInteger.valueOf(54321L));
    pct.start();
    Thread.sleep(1000);
    pct.interrupt();
  }
}
