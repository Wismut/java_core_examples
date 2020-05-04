package main.java.com.wismut.javacore.chapter28;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    public static void main(String[] args) {

    }
}

class MyThread3 implements Runnable {
    Phaser phsr;
    String name;

    MyThread3(Phaser p, String n) {
        phsr = p;
        name = n;
        phsr.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " beginning phase one");
        phsr.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Thread " + name + " beginning phase two");
        phsr.arriveAndAwaitAdvance();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Thread " + name + " beginning phase three");
        phsr.arriveAndDeregister();
    }
}
