package com.design.u055;

import javafx.collections.WeakSetChangeListener;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * 仿照字符串常量池的话，大概率还是有一个静态cache类存储着所有的缓存对象的，但这个引用肯定是弱引用，否则GC 无法回收。
 * 但是用什么数据结构来存储这些缓存对象呢？
 * 如果是list，查找或插入 效率不高；hashMap感觉没必要再存储个value;Set 好像又没有返回int 对应Integer对象的方法。
 */
public class IntegerCache {
    public static HashSet<WeakReference<Integer>> intergerCache;

//    public Integer valueOf(int value) {
//        if (intergerCache.contains(value)) {
//            return intergerCache
//        }
//    }
}
