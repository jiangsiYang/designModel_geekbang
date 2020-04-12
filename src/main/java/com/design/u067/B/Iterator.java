package com.design.u067.B;

public interface Iterator<E> {
    boolean hasNext();

    E next();

    E currentItem();
}
