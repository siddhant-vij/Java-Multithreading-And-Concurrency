package com.multithreadingconcurrency.p03CriticalSection.DeadlockDemo;

public class MainSim {
  public static void main(String[] args) {
    Intersection intersection = new Intersection();
    FirstTrain firstTrain = new FirstTrain(intersection);
    SecondTrain secondTrain = new SecondTrain(intersection);

    firstTrain.start();
    secondTrain.start();
  }  
}
