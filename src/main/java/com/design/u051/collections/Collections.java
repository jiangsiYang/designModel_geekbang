package com.design.u051.collections;

import java.util.Collection;
import java.util.Iterator;

/**
 * 在做版本升级的时候，对于一些要废弃的接口，我们不直接将其删除，而是暂时保留，并且标注为 deprecated，并将内部实现逻辑委托为新的接口实现。
 */
public class Collections {
    public static Emueration emumeration(final Collection c) {
        return new Emueration() {
            Iterator i = c.iterator();

            public boolean hasMoreElments() {
                return i.hasNext();
            }

            public Object nextElement() {
                return i.next();
            }
        };
    }
}
