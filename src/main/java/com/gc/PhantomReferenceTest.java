package com.gc;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PhantomReferenceTest {

    public static boolean isRun = true;
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        new Thread() {
            public void run() {
                while (isRun) {
                    Object obj = referenceQueue.poll();
                    if (obj != null) {
                        try {
                            Field rereferent = Reference.class
                                    .getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(obj);
                            System.out.println("gc will collect："
                                    + result.getClass() + "@"
                                    + result.hashCode() + "\t"
                                    + (String) result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
        abc = null;
        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);
        isRun = false;
    }


    /**
     * 简单使用虚引用demo
     * 虚引用在实现一个对象被回收之前必须做清理操作是很有用的,比finalize()方法更灵活
     */
    private static void simpleUsePhantomRefDemo() throws InterruptedException {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomRef = new PhantomReference<>(obj, refQueue);
        // null
        System.out.println(phantomRef.get());
        // null
        System.out.println(refQueue.poll());
        obj = null;
        // 通知JVM的gc进行垃圾回收
        System.gc();
        // null, 调用phantomRef.get()不管在什么情况下会一直返回null
        System.out.println(phantomRef.get());
        // 当GC发现了虚引用，GC会将phantomRef插入进我们之前创建时传入的refQueue队列
        // 注意，此时phantomRef对象，并没有被GC回收，在我们显式地调用refQueue.poll返回phantomRef之后
        // 当GC第二次发现虚引用，而此时JVM将phantomRef插入到refQueue会插入失败，此时GC才会对phantomRef对象进行回收
        Thread.sleep(200);
        Reference<?> pollObj = refQueue.poll();
        // java.lang.ref.PhantomReference@1540e19d
        System.out.println(pollObj);
        if (null != pollObj) {
            // 进行资源回收的操作
        }
    }
}
