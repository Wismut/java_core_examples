package main.java.com.wismut.javacore.chapter15;

interface StringFunc2 {
    String func(String n);
}

public class LambdasAsArgumentsDemo {
    static String stringOp(StringFunc2 sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "Lambdas add power to Java";
        String outStr;

        System.out.println("Here is input string: " + inStr);

        outStr = stringOp((str) -> str.toUpperCase(), inStr);
        System.out.println("The string in uppercase: " + outStr);

        outStr = stringOp((str) -> {
            String result = "";
            int i;

            for (i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ') {
                    result += str.charAt(i);
                }
            }

            return result;
        }, inStr);

        System.out.println("The string with spaces removed: " + outStr);

        StringFunc2 reverse = (str) -> {
            String result = "";
            int i;

            for (i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }

            return result;
        };

        System.out.println("The string reversed: " + stringOp(reverse, inStr));
    }
}
