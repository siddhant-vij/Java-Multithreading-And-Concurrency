package com.multithreadingconcurrency.p01ThreadsBasics.PoliceHackerThreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGame {
  public static void main(String[] args) {
    PasswordVault passwordVault = new PasswordVault(new Random().nextInt(10000));
    List<Thread> threads = new ArrayList<>();
    threads.add(new AscendingHacker(passwordVault));
    threads.add(new DescendingHacker(passwordVault));
    threads.add(new Police());
    for (Thread thread : threads) {
      thread.start();
    }
  }  
}
