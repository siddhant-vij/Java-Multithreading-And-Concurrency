package com.multithreadingconcurrency.p04AdvancedLocking.ReentrantLockUI;

import javax.swing.SwingUtilities;

public class MainApp {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new UserInterface();
      }
    });
  }
}
