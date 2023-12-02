package com.multithreadingconcurrency.p05InterThreadComms.Phaser;

import java.util.concurrent.Phaser;

public class CustomPhaser extends Phaser {
  int numPhases;

  CustomPhaser(int parties, int numPhases) {
    super(parties);
    this.numPhases = numPhases;
  }

  @Override
  protected boolean onAdvance(int curPhase, int registeredParties) {
    System.out.println("Phase: " + curPhase + " completed.\n");
    if (curPhase == numPhases - 1 || registeredParties == 0) {
      return true;
    }
    return false;
  }
}