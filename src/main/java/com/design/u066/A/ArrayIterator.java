package com.design.u066.A;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayIterator<E> implements Iterator<E> {

    private int cursor;
    private ArrayList<E> arrayList;
    private int expectedModCount;

    public ArrayIterator(ArrayList arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
        this.expectedModCount = arrayList.getModCount();
    }

    /**
     * 每次调用迭代器上的 hasNext()、next()、currentItem()函数，都会检查集合上的 modCount 是否等于 expectedModCount，也就是看，在创建完迭代器之后，modCount 是否改变过。
     */
    private void checkForComodification() {
        if (arrayList.getModCount() != expectedModCount) throw new ConcurrentModificationException();
    }

    @Override
    public boolean hasNext() {
        //注意这里，cursor在指向最后一个元素的时候，hasNext()仍旧返回true。这里要配合demo来看比较好理解一点。
        checkForComodification();
        return cursor != arrayList.size();
    }

    @Override
    public void next() {
        checkForComodification();
        cursor++;
    }

    @Override
    public E currentItem() {
        checkForComodification();
        if (cursor >= arrayList.size()) {
            throw new NoSuchElementException();
        }
        return arrayList.get(cursor);
    }
}
