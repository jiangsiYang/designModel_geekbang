package com.design.u067.A;

import java.lang.reflect.Array;

public class ArrayList<E> implements List<E> {
    E[] elementData;

    private int size = 0;

    private int modCount = 0;


    private static final int DEFAULT_SIZE = 8;

    public ArrayList(Class<E> type) {
        //        elementData = new E[];    //这里为啥不能用泛型初始化数组啊
        elementData = (E[]) Array.newInstance(type, DEFAULT_SIZE);
    }

    public ArrayList() {
    }

    @Override
    public Iterator iterator() {
        return new SnapshotArrayIterator(this);
    }

    @Override
    public int size() {
        return elementData.length;
    }

    @Override
    public E get(int i) {
        return elementData[i];
    }

    @Override
    public void add(E e) {
        elementData[size++] = e;
    }

    public int getModCount() {
        return modCount;
    }

    public void remove(int index) {
        modCount++;
    }

    public void addAll(ArrayList<E> arrayList) {
    }
}
