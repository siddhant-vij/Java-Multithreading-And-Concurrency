package com.multithreadingconcurrency.p04AdvancedLocking.ReentrantLockUI;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PricesContainer {
  private Lock lockObject = new ReentrantLock();

  private double stock1Price;
  private double stock2Price;
  private double stock3Price;
  private double stock4Price;
  private double stock5Price;

  public Lock getLockObject() {
    return lockObject;
  }

  public double getStock1Price() {
    return stock1Price;
  }

  public void setStock1Price(double stock1Price) {
    this.stock1Price = stock1Price;
  }

  public double getStock2Price() {
    return stock2Price;
  }

  public void setStock2Price(double stock2Price) {
    this.stock2Price = stock2Price;
  }

  public double getStock3Price() {
    return stock3Price;
  }

  public void setStock3Price(double stock3Price) {
    this.stock3Price = stock3Price;
  }

  public double getStock4Price() {
    return stock4Price;
  }

  public void setStock4Price(double stock4Price) {
    this.stock4Price = stock4Price;
  }

  public double getStock5Price() {
    return stock5Price;
  }

  public void setStock5Price(double stock5Price) {
    this.stock5Price = stock5Price;
  }

}
