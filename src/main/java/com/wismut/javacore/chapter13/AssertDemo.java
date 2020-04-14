package main.java.com.wismut.javacore.chapter13;

public class AssertDemo {
    static int val = 3;

    static int getNum() {
        return val--;
    }

    public static void main(String[] args) {
        int n = 0;

        for (int i = 0; i < 10; i++) {
//            n = getNum();

            assert ((n = getNum()) > 0) : "n isn't positive";

            System.out.println("n is: " + n);
        }
    }
}
