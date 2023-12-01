package com.multithreadingconcurrency.p04AdvancedLocking.ReentrantLockUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class UserInterface extends JFrame {

  private AtomicInteger counter = new AtomicInteger(0);
  private JLabel counterLabel;

  public UserInterface() {
    // Initialize GUI components
    counterLabel = new JLabel("Counter: " + counter.get());
    JButton incrementButton = new JButton("Increment");
    JButton decrementButton = new JButton("Decrement");

    // Set up action listeners for buttons
    incrementButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        counter.incrementAndGet();
        updateCounterDisplay();
      }
    });

    decrementButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        counter.decrementAndGet();
        updateCounterDisplay();
      }
    });

    // Layout
    setLayout(new FlowLayout());
    add(counterLabel);
    add(incrementButton);
    add(decrementButton);

    // Frame settings
    setSize(300, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // Start price updater - Terminal thread
    PricesContainer pricesContainer = new PricesContainer();
    PriceUpdater priceUpdaterThread = new PriceUpdater(pricesContainer);
    priceUpdaterThread.start();
  }

  private void updateCounterDisplay() {
    counterLabel.setText("Counter: " + counter.get());
  }

  public int getCounterValue() {
    return counter.get();
  }
}
