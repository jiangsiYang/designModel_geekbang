package com.design.u066.B;

public interface Iterator<E> {
    boolean hasNext();

    E next();

    E currentItem();
}
