package com.design.u065;


public class Demo {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>(String.class);
        names.add("xzg");
        names.add("wang");
        names.add("zheng");
        Iterator<String> iterator = new ArrayIterator(names);
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }
}
