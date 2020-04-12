package com.design.u066.B;

public interface List<E> {
    Iterator iterator();
    int size();
    E get(int i);
    void add(E e);
}
