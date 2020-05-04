package main.java.com.wismut.javacore.chapter28;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) {
        new AtomThread("A");
        new AtomThread("B");
        new AtomThread("C");
    }
}

class Shared3 {
    static AtomicInteger ai = new AtomicInteger(0);
}

class AtomThread implements Runnable {
    String name;

    AtomThread(String n) {
        name = n;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Starting " + name);
        for (int i = 1; i <= 3; i++) {
            System.out.println(name + " got: " + Shared3.ai.getAndSet(i));
        }
    }
}
