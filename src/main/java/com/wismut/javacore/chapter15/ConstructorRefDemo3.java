package main.java.com.wismut.javacore.chapter15;

interface MyFunc6<R, T> {
    R func(T n);
}

class MyClass4<T> {
    private T val;

    MyClass4(T v) {
        val = v;
    }

    MyClass4() {
        val = null;
    }

    T getVal() {
        return val;
    }
}

class MyClass5 {
    String str;

    MyClass5(String s) {
        str = s;
    }

    MyClass5() {
        str = null;
    }

    String getVal() {
        return str;
    }
}


public class ConstructorRefDemo3 {
    static <R, T> R myClassFactory(MyFunc6<R, T> cons, T v) {
        return cons.func(v);
    }
}
