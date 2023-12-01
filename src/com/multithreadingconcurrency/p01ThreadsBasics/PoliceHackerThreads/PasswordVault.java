package com.multithreadingconcurrency.p01ThreadsBasics.PoliceHackerThreads;

public class PasswordVault {
  private int password;

  public PasswordVault(int password) {
    this.password = password;
  }

  public boolean isCorrectPassword(int password) {
    try {
      Thread.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return this.password == password;
  }
}
