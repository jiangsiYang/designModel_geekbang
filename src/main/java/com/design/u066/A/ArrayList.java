package com.design.u066.A;

import java.lang.reflect.Array;

/**
 * 怎么确定在遍历时候，集合有没有增删元素呢？我们在 ArrayList 中定义一个成员变量 modCount，记录集合被修改的次数，集合每调用一次增加或删除元素的函数，就会给 modCount 加 1。
 * 当通过调用集合上的 iterator() 函数来创建迭代器的时候，我们把 modCount 值传递给迭代器的 expectedModCount 成员变量，之后每次调用迭代器上的 hasNext()、next()、currentItem()
 * 函数，我们都会检查集合上的 modCount 是否等于 expectedModCount，也就是看，在创建完迭代器之后，modCount 是否改变过。
 *
 * @param <E>
 */
public class ArrayList<E> implements List<E> {
    E[] elementData;

    private int size = 0;

    private int modCount = 0;

    private static final int DEFAULT_SIZE = 8;

    public ArrayList(Class<E> type) {
        //        elementData = new E[];    //这里为啥不能用泛型初始化数组啊
        elementData = (E[]) Array.newInstance(type, DEFAULT_SIZE);
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator(this);
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
        modCount++;
    }

    public void remove(int index){
        modCount++;
    }

    public int getModCount() {
        return modCount;
    }
}
