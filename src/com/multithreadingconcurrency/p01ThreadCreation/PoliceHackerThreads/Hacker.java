package com.multithreadingconcurrency.p01ThreadCreation.PoliceHackerThreads;

public abstract class Hacker extends Thread {
  protected PasswordVault passwordVault;
  protected final int MAX_GUESSES = 10000;

  public Hacker(PasswordVault passwordVault) {
    this.passwordVault = passwordVault;
    this.setName(this.getClass().getSimpleName());
    this.setPriority(MAX_PRIORITY);
  }

  @Override
  public void start() {
    System.out.println("Starting " + this.getClass().getSimpleName() + " thread");
    super.start();
  }
}
