package com.design.u065;

public interface List<E> {
    Iterator iterator();
    int size();
    E get(int i);
    void add(E e);
}
