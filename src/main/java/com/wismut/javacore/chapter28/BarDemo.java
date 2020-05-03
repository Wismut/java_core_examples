package main.java.com.wismut.javacore.chapter28;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
        System.out.println("Starting");
        new MyThread2(cb, "A");
        new MyThread2(cb, "B");
        new MyThread2(cb, "C");
        new MyThread2(cb, "X");
        new MyThread2(cb, "Y");
        new MyThread2(cb, "Z");
    }
}

class MyThread2 implements Runnable {
    CyclicBarrier cbar;
    String name;

    MyThread2(CyclicBarrier c, String n) {
        cbar = c;
        name = n;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println(name);
        try {
            int await = cbar.await();
            System.out.println(name + ": " + await);
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (BrokenBarrierException e) {
            System.out.println(e);
        }
    }
}

class BarAction implements Runnable {
    @Override
    public void run() {
        System.out.println("Barrier reached!");
    }
}
