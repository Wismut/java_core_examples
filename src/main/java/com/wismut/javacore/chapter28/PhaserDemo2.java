package main.java.com.wismut.javacore.chapter28;

import java.util.concurrent.Phaser;

class MyPhaser extends Phaser {
    int numPhases;

    MyPhaser(int parties, int phaseCount) {
        super(parties);
        numPhases = phaseCount - 1;
    }

    @Override
    protected boolean onAdvance(int p, int regParties) {
        System.out.println("Phase " + p + " completed.\n");
        if (p == numPhases || regParties == 0) {
            return true;
        }
        return false;
    }
}

public class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser phsr = new MyPhaser(1, 4);
        System.out.println("Starting\n");
        new MyThread4(phsr, "A");
        new MyThread4(phsr, "B");
        new MyThread4(phsr, "C");
        while (!phsr.isTerminated()) {
            phsr.arriveAndAwaitAdvance();
        }
        System.out.println("The phaser is terminated");
    }
}

class MyThread4 implements Runnable {
    Phaser phsr;
    String name;

    MyThread4(Phaser p, String n) {
        phsr = p;
        name = n;
        phsr.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (!phsr.isTerminated()) {
            System.out.println("Thread " + name + " beginning phase " + phsr.getPhase());
            phsr.arriveAndAwaitAdvance();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}