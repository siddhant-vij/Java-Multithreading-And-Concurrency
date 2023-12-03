package com.multithreadingconcurrency.p06InterThreadComms.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class MatricesReaderProducer implements Runnable {
  private ThreadSafeQueue queue;
  private BufferedReader reader;
  private int N;

  public MatricesReaderProducer(ThreadSafeQueue queue, BufferedReader reader, int N) {
    this.queue = queue;
    this.reader = reader;
    this.N = N;
  }

  @Override
  public void run() {
    while (true) {
      double[][] matrix1 = null;
      double[][] matrix2 = null;
      try {
        matrix1 = readMatrixFromFile();
        matrix2 = readMatrixFromFile();
      } catch (IOException e) {
      }
      if (matrix1 == null || matrix2 == null) {
        queue.terminate();
        // System.out.println("No more matrices to read. Producer Thread is terminating");
        return;
      }
      MatricesPair matricesPair = new MatricesPair();
      matricesPair.setMatrix1(matrix1);
      matricesPair.setMatrix2(matrix2);
      queue.add(matricesPair);
    }
  }

  private double[][] readMatrixFromFile() throws IOException {
    double[][] matrix = new double[N][N];
    for (int i = 0; i < N; i++) {
      String line = reader.readLine();
      if (line == null) {
        return null;
      }
      String[] row = line.split(",");
      for (int j = 0; j < N; j++) {
        matrix[i][j] = Double.parseDouble(row[j]);
      }
    }
    reader.readLine();
    return matrix;
  }
}
