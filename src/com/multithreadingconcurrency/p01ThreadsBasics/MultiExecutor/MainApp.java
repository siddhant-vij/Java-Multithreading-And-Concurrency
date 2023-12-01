package com.multithreadingconcurrency.p01ThreadsBasics.MultiExecutor;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
  public static void main(String[] args) {
    List<Runnable> tasks = new ArrayList<>();
    tasks.add(() -> System.out.println("Task 1"));
    tasks.add(() -> System.out.println("Task 2"));
    tasks.add(() -> System.out.println("Task 3"));
    tasks.add(() -> System.out.println("Task 4"));
    tasks.add(() -> System.out.println("Task 5"));
    
    MultiExecutor multiExecutor = new MultiExecutor(tasks);
    multiExecutor.executeAll();
  }
}
