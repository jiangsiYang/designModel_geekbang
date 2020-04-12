package com.design.u067.A;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * 迭代器类中除了前面提到的几个最基本的方法之外，还定义了一个 remove() 方法，能够在遍历集合的同时，安全地删除集合中的元素。不过，需要说明的是，它并没有提供添加元素的方法。
 * 迭代器类新增了一个 lastRet 成员变量，用来记录游标指向的前一个元素。通过迭代器去删除这个元素的时候，我们可以更新迭代器中的游标和 lastRet 值，来保证不会因为删除元素而导
 * 致某个元素遍历不到。如果通过容器来删除元素，并且希望更新迭代器中的游标值来保证遍历不出错，我们就要维护这个容器都创建了哪些迭代器，每个迭代器是否还在使用等信息，代码实
 * 现就变得比较复杂了。
 *
 * @param <E>
 */
public class ArrayIterator<E> implements Iterator<E> {

    private int cursor;
    private ArrayList<E> arrayList;

    int lastRet = -1; // index of last element returned; -1 if no such
    int expectedModCount;

    public ArrayIterator(ArrayList arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
        this.expectedModCount = arrayList.getModCount();
    }

    @Override
    public boolean hasNext() {
        //注意这里，cursor在指向最后一个元素的时候，hasNext()仍旧返回true。这里要配合demo来看比较好理解一点。
        return cursor != arrayList.size();
    }

    @Override
    public E next() {
        checkForComodification();
        int i = cursor;
        if (i >= arrayList.size()) throw new NoSuchElementException();
        Object[] elementData = arrayList.elementData;
        if (i >= elementData.length) throw new ConcurrentModificationException();
        cursor = i + 1;
        //注意，每次next执行的时候，都将lastRet 指向前一个
        return (E) elementData[lastRet = i];
    }

    @Override
    public E currentItem() {
        if (cursor >= arrayList.size()) {
            throw new NoSuchElementException();
        }
        return arrayList.get(cursor);
    }

    private void checkForComodification() {
        if (arrayList.getModCount() != expectedModCount) throw new ConcurrentModificationException();
    }

    public void remove() {
        if (lastRet < 0) throw new IllegalStateException();
        checkForComodification();
        try {
            arrayList.remove(lastRet);
            //remove之后要将cursor调整为之前的，即lastRet
            cursor = lastRet;
            //一个next只能跟一个remove，不允许多个remove
            lastRet = -1;
            //重新对齐expectedModCount=modCount，否则会在下一个checkForComodification()的时候报错
            expectedModCount = arrayList.getModCount();
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }
}
