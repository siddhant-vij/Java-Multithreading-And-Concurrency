package com.multithreadingconcurrency.p01ThreadsBasics.PoliceHackerThreads;

public class AscendingHacker extends Hacker {
  public AscendingHacker(PasswordVault passwordVault) {
    super(passwordVault);
  }

  @Override
  public void run() {
    int password = 0;
    while (password < MAX_GUESSES) {
      if (passwordVault.isCorrectPassword(password)) {
        System.out.println(this.getClass().getSimpleName() + " found password: " + password);
        System.exit(0);
      }
      password++;
    }
  }
}
