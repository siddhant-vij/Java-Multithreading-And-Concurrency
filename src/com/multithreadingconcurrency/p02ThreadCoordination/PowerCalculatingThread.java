package com.multithreadingconcurrency.p02ThreadCoordination;

import java.math.BigInteger;

public class PowerCalculatingThread extends Thread {
  private BigInteger result = BigInteger.ONE;
  private BigInteger base;
  private BigInteger power;

  public PowerCalculatingThread(BigInteger base, BigInteger power) {
    this.base = base;
    this.power = power;
  }

  @Override
  public void run() {
    System.out.println(String.format("%s ^ %s = %s", base, power, power()));
  }

  private BigInteger power() {
    for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
      if (Thread.currentThread().isInterrupted()) {
        System.out.println("Thread interrupted");
        return BigInteger.ZERO;
      }
      result = result.multiply(base);
    }
    return result;
  }

  public BigInteger getResult() {
    return result;
  }
}
