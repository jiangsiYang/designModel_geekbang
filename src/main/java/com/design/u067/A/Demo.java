package com.design.u067.A;


public class Demo {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>(String.class);
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        Iterator<String> iterator = new ArrayIterator(names);
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }
}
