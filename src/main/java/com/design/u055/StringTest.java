package com.design.u055;

public class StringTest {
    public static void main(String args[]) {
        String a = "ab";
        String b = "a" + "b";
        String c = "ab";
        String d = new String("ab");


        String e1 = "dc";
        String e2 = new String("dc");
        e2 = e2.intern();   //如果只是e2.intern(),没有重新赋值给e2,那么还会是false

        String f = new String("ef");
        f = f.intern();
        String g = "ef";


        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d);
        System.out.println(e1 == e2);
        System.out.println(f == g);
    }
}
