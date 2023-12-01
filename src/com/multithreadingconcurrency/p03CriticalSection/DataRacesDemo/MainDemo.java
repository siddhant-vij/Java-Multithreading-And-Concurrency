package com.multithreadingconcurrency.p03CriticalSection.DataRacesDemo;

public class MainDemo {
  public static void main(String[] args) {
    SharedClass sharedClass = new SharedClass();

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        sharedClass.increment();
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        sharedClass.checkForDataRace();
      }
    });

    thread1.start();
    thread2.start();
  }

  public static class SharedClass {
    private volatile int x = 0;
    private volatile int y = 0;

    // Data Race Condition (Visibility Issues) are solved by volatile keyword

    public void increment() {
      x++;
      y++;
    }

    public void checkForDataRace() {
      if (y > x) {
        System.out.println("y > x - Data Race is detected");
      }
    }
  }
}
