package com.multithreadingconcurrency.p05InterThreadComms.Exchanger;

/*
 * Practical Example: Producer-Consumer Data Exchange
 * 
 * In this example, we'll demonstrate a simple producer-consumer scenario
 * where a producer thread produces data and uses an Exchanger to pass it
 * to a consumer thread.
 */

public class ExchangerDemo {
  public static void main(String[] args) {
    new DataExchange().startExchange();
  }
}
