package main.java.com.wismut.javacore.chapter20;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;

public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {
        String s = "This; is a &copy; copyright symbol " +
                "but & this is &copy not.\n";
        char[] buf = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);

        CharArrayReader in = new CharArrayReader(buf);
        int c;
        boolean marked = false;

        try (BufferedReader f = new BufferedReader(in)) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '&':
                        if (!marked) {
                            marked = true;
                            f.mark(32);
                        } else {
                            marked = false;
                        }
                        break;
                    case ';':
                        if (marked) {
                            System.out.print("(c)");
                            marked = false;
                        } else {
                            System.out.print((char) c);
                        }
                        break;
                    case ' ':
                        if (marked) {
                            f.reset();
                            marked = false;
                            System.out.print('&');
                        } else {
                            System.out.print((char) c);
                        }
                        break;
                    default:
                        if (!marked) {
                            System.out.print((char) c);
                        }
                        break;
                }
            }
        }
    }
}
