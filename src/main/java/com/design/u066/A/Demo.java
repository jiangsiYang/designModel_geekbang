package com.design.u066.A;


public class Demo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(String.class);
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        Iterator iterator = names.iterator();
        iterator.next();
        names.remove(0);
        iterator.next();//抛出ConcurrentModificationException异常
    }
}
