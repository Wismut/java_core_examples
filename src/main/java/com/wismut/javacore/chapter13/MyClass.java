package main.java.com.wismut.javacore.chapter13;

public class MyClass {
    int a;
    int b;

    MyClass(int i, int j) {
        this.a = i;
        this.b = j;
    }

    MyClass(int i) {
        this(i, i);
    }

    MyClass() {
        this(0);
    }
}
