package com.design.u065;

public interface Iterator<E> {
    boolean hasNext();

    void next();

    E currentItem();
}
