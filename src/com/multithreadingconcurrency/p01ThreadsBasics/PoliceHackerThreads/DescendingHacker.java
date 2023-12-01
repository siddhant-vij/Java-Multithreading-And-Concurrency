package com.multithreadingconcurrency.p01ThreadsBasics.PoliceHackerThreads;

public class DescendingHacker extends Hacker {
  public DescendingHacker(PasswordVault passwordVault) {
    super(passwordVault);
  }

  @Override
  public void run() {
    int password = MAX_GUESSES;
    while (password >= 0) {
      if (passwordVault.isCorrectPassword(password)) {
        System.out.println(this.getClass().getSimpleName() + " found password: " + password);
        System.exit(0);
      }
      password--;
    }
  }
}
