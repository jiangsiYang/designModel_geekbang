package com.design.u055;

public class IntegerTest {
    public static void main(String args[]) {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        int c = 1;
        Integer d = 1;
        Integer e = 1;
        Integer f = 128;
        Integer g = 128;

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(c == d);
        System.out.println(a == d);
        System.out.println(d == e);
        System.out.println(f == g);
    }
}
