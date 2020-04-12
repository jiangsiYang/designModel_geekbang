package com.design.u067.A;

public interface Iterator<E> {
    boolean hasNext();

    E next();

    E currentItem();
}
