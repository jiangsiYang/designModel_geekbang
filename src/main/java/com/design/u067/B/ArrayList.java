package com.design.u067.B;

import java.lang.reflect.Array;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    E[] elementData;

    private int actualSize; //不包含标记删除元素
    private int totalSize; //包含标记删除元素

    /**
     * 各个元素对应的添加/删除时间戳存储到下面的数组里
     */
    private long[] addTimestamps;
    private long[] delTimestamps;

    private int modCount = 0;


    private static final int DEFAULT_SIZE = 8;

    Class<E> type;

    public ArrayList(Class<E> type) {
        //        elementData = new E[];    //这里为啥不能用泛型初始化数组啊
        this.type = type;
        elementData = (E[]) Array.newInstance(type, DEFAULT_SIZE);
    }

    public ArrayList() {
        this.elementData = (E[]) Array.newInstance(type, DEFAULT_SIZE);
        this.addTimestamps = new long[DEFAULT_CAPACITY];
        this.delTimestamps = new long[DEFAULT_CAPACITY];
        this.totalSize = 0;
        this.actualSize = 0;
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
        elementData[actualSize] = e;
        addTimestamps[totalSize] = System.currentTimeMillis();
        delTimestamps[totalSize] = Long.MAX_VALUE;
        totalSize++;
        actualSize++;
    }

    public int getModCount() {
        return modCount;
    }

    public void remove(int index) {
        modCount++;
    }

    /**
     * 这里的remove 不真正删除元素，只是维护delTimestamps 及 actualSize
     *
     * @param obj
     */
    public void remove(E obj) {
        for (int i = 0; i < totalSize; ++i) {
            if (elementData[i].equals(obj)) {
                delTimestamps[i] = System.currentTimeMillis();
                actualSize--;
            }
        }
    }

    public long getAddTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return addTimestamps[i];
    }

    public long getDelTimestamp(int i) {
        if (i >= totalSize) {
            throw new IndexOutOfBoundsException();
        }
        return delTimestamps[i];
    }


    public void addAll(ArrayList<E> arrayList) {
    }


    public int getActualSize() {
        return actualSize;
    }

    public int getTotalSize() {
        return totalSize;
    }


}
