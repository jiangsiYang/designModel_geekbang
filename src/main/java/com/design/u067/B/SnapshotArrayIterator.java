package com.design.u067.B;

/**
 * 容器创建迭代器的时候，相当于给容器拍了一张快照（Snapshot）。之后即便我们增删容器中的元素，快照中的元素并不会做相应的改动。
 * 而迭代器遍历的对象是快照而非容器，这样就避免了在使用迭代器遍历的过程中，增删容器中的元素，导致的不可预期的结果或者报错。
 * <p>
 * 第二种解决方案，可以在容器中，为每个元素保存两个时间戳，一个是添加时间戳 addTimestamp，一个是删除时间戳 delTimestamp。当元素被加入到集合中的时候，
 * 我们将 addTimestamp 设置为当前时间，将 delTimestamp 设置成最大长整型值（Long.MAX_VALUE）。当元素被删除时，我们将 delTimestamp 更新为当前时间，表示已经被删除。
 * 实际上跟Mysql的mvcc版本的思路是一样的，每开始一个事务即去获取一个对应的事务版本号，只查询符合这个版本号的数据，保证数据的可重复读。
 * <p>
 * 存在问题：代码会比较复杂，存在三个比较明显的问题:第一个是容器的元素都不是真正删除，会一直存在，占用空间；第二个是，如果底层是数组，还需要额外维护一个数组，一个是支持标记
 * 删除的，用来实现快照遍历功能；一个是不支持标记删除的（即真正要将删除元素直接从数据中移除），用来支持随机访问。第三个是，需要考虑维护的地方会比较多，比如扩容的问题。
 *
 * 解决思路:我觉得既然跟Mysql的mvcc是一致的思维，那么针对这个问题的解决方案应该也是差不多的。我猜，在mvcc里，当所有事务都提交后，标记删除的元素会真正的删除，这里应该也是如此，
 * 当检测到所有迭代器完成后，应该会把标记删除的元素真正的删除，只是这个时间点不懂在哪里维护。
 * 或许可以在每个迭代器开始时的时间戳维护在容器里的一个列表里，每个迭代器完成再把这个列表里的自己的时间戳删除掉，这样就可以知道每个容器还有哪些迭代器未使用完。当这个列表的时间戳
 * 都大于标记删除的元素的时间戳，就说明已经没有迭代器能遍历到这个元素了，这个元素就可以真正的从列表中删除了。
 * 每个迭代器完成迭代后，都可以进行这一步移除标记删除元素的动作。
 *
 * @param <E>
 */
public class SnapshotArrayIterator<E> implements Iterator<E> {
    private long snapshotTimestamp;
    private int cursorInAll; // 在整个容器中的下标，而非快照中的下标
    private int leftCount; // 快照中还有几个元素未被遍历
    private ArrayList<E> arrayList;

    public SnapshotArrayIterator(ArrayList<E> arrayList) {
        //先取当前时间作为迭代器的版本号
        this.snapshotTimestamp = System.currentTimeMillis();
        this.cursorInAll = 0;
        //将容器中的真正未删除的元素作为迭代器此刻的容器数量
        this.leftCount = arrayList.getActualSize();
        this.arrayList = arrayList;
        justNext(); // 先找到第一个符合当前版本号的元素
    }

    @Override
    public boolean hasNext() {
        return this.leftCount >= 0; // 注意是>=, 而非>
    }

    @Override
    public E next() {
        E currentItem = arrayList.get(cursorInAll);
        //每次next，都去拿下一个符合版本号的元素
        justNext();
        return currentItem;
    }

    @Override
    public E currentItem() {
        return null;
    }

    /**
     * 从容器起始元素开始遍历，每次找到一个符合版本的元素，直到整个容器被遍历完
     */
    private void justNext() {
        while (cursorInAll < arrayList.getTotalSize()) {
            long addTimestamp = arrayList.getAddTimestamp(cursorInAll);
            long delTimestamp = arrayList.getDelTimestamp(cursorInAll);
            if (snapshotTimestamp > addTimestamp && snapshotTimestamp < delTimestamp) {
                leftCount--;
                break;
            }
            cursorInAll++;
        }
    }
}
