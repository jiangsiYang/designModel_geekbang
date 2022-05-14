package com.design.u050.student;

/**
 * 不同的学生有不同的性格,通过装饰器模式可以叠加不同的性格
 */
public class Demo {
    public static void main(String args[]) {
        IStudent A = new BoyStudent();
        A = new CalmStudent(A);
        A = new BraveStudent(A);
        A = new GoodnessStudent(A);
        System.out.println(A.character());
        System.out.println("魅力值:" + A.charmValue());

        IStudent B = new BoyStudent();
        B = new GoodnessStudent(B);
        B = new ImpertinencyStudent(B);
        System.out.println(B.character());
        System.out.println("魅力值:" + B.charmValue());
    }
}
