package com.design.u055;

public class StringTest {
    public static void main(String args[]) {
        String a = "ab";
        String b = "a" + "b";
        String c = "ab";
        String d = new String("ab");

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d);
    }
}
