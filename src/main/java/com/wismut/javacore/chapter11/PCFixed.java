package main.java.com.wismut.javacore.chapter11;

class Q2 {
    int n;
    boolean valueSet = false;

    synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception caught");
            }
        }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception caught");
            }
        }
        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}

class Producer2 implements Runnable {
    Q2 q;

    Producer2(Q2 q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }
    }
}

class Consumer2 implements Runnable {
    Q2 q;

    Consumer2(Q2 q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        while (true) {
            q.get();
        }
    }
}

public class PCFixed {
    public static void main(String[] args) {
        Q2 q = new Q2();
        new Producer2(q);
        new Consumer2(q);
        System.out.println("Press Control-C to stop.");
    }
}
