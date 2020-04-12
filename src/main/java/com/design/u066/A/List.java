package com.design.u066.A;

public interface List<E> {
    Iterator iterator();
    int size();
    E get(int i);
    void add(E e);
    void remove(int i);
}
