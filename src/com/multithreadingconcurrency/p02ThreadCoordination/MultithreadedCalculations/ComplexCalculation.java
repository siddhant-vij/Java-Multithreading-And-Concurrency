package com.multithreadingconcurrency.p02ThreadCoordination.MultithreadedCalculations;

import java.math.BigInteger;

import com.multithreadingconcurrency.p02ThreadCoordination.PowerCalculatingThread;

public class ComplexCalculation {
  public static void main(String[] args) {
    BigInteger base1 = BigInteger.valueOf(123);
    BigInteger power1 = BigInteger.valueOf(11);
    BigInteger base2 = BigInteger.valueOf(11);
    BigInteger power2 = BigInteger.valueOf(123);
    System.out.println(calculateResult(base1, power1, base2, power2));
  }

  public static BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
    BigInteger result;
    PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
    PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
    thread1.start();
    thread2.start();
    try {
      thread1.join();
      thread2.join();
      result = thread1.getResult().add(thread2.getResult());
      return result;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }
}
