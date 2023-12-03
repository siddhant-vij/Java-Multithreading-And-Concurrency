package com.multithreadingconcurrency.p06InterThreadComms;

/*
 * Step 1: Run MatricesGenerator.java
 * Step 2: Run MainApp.java
 */

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class MatricesGenerator {
  private static final int NUM_ROWS = 10;
  private static final int NUM_COLS = 10;
  public static final int NUM_MATRICES = 100;
  private static final String INPUT_FILE_PATH = "src\\com\\multithreadingconcurrency\\p06InterThreadComms\\data\\MatricesDatabase.txt";

  public static int getNumMatrices() {
    return NUM_MATRICES;
  }

  public static void main(String[] args) {
    for (int i = 0; i < NUM_MATRICES; i++) {
      double[][] matrix = generateMatrix();
      try {
        addMatrixToFile(matrix);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

  private static double[] generateRow() {
    double[] row = new double[NUM_ROWS];
    for (int i = 0; i < NUM_ROWS; i++) {
      row[i] = Math.random() * 100;
    }
    return row;
  }

  private static double[][] generateMatrix() {
    double[][] matrices = new double[NUM_ROWS][NUM_COLS];
    for (int i = 0; i < NUM_COLS; i++) {
      matrices[i] = generateRow();
    }
    return matrices;
  }

  private static void addMatrixToFile(double[][] matrix) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(new FileOutputStream(INPUT_FILE_PATH, true));
    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
        if (j == NUM_COLS - 1) {
          writer.print(String.format("%.2f", matrix[i][j]));
        } else {
          writer.print(String.format("%.2f", matrix[i][j]) + ",");
        }
      }
      writer.println();
    }
    writer.println();
    writer.flush();
    writer.close();
  }
}
