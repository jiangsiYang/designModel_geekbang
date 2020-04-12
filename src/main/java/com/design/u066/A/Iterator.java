package com.design.u066.A;

public interface Iterator<E> {
    boolean hasNext();

    void next();

    E currentItem();
}
