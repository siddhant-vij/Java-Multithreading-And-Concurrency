package com.multithreadingconcurrency.p06InterThreadComms.utils;

import java.io.PrintWriter;

public class MatricesMultiplierConsumer implements Runnable {
  private ThreadSafeQueue queue;
  private PrintWriter writer;

  public MatricesMultiplierConsumer(ThreadSafeQueue queue, PrintWriter writer) {
    this.queue = queue;
    this.writer = writer;
  }

  @Override
  public void run() {
    while (true) {
      MatricesPair matricesPair = queue.remove();
      if (matricesPair == null) {
        // System.out.println("No more matrices to read from the queue, consumer is terminating");
        break;
      }
      double[][] result = multiplyMatrices(matricesPair.getMatrix1(),
          matricesPair.getMatrix2());
      saveMatrixToFile(result);
    }
    writer.flush();
    writer.close();
  }

  private void saveMatrixToFile(double[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (j == matrix[0].length - 1) {
          writer.print(String.format("%.2f", matrix[i][j]));
        } else {
          writer.print(String.format("%.2f", matrix[i][j]) + ",");
        }
      }
      writer.println();
    }
    writer.println();
  }

  private double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
    double[][] result = new double[matrix1.length][matrix2[0].length];
    for (int i = 0; i < matrix1.length; i++) {
      for (int j = 0; j < matrix2[0].length; j++) {
        for (int k = 0; k < matrix1[0].length; k++) {
          result[i][j] += matrix1[i][k] * matrix2[k][j];
        }
      }
    }
    return result;
  }
}
