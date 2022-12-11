package com.gc;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.ref.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PhantomReferenceTest {

    public static boolean isRun = true;

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        simpleUsePhantomRefDemo();
    }

    private static void test1() throws InterruptedException {
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
     * 简单使用非强引用demo
     * 虚引用在实现一个对象被回收之前必须做清理操作是很有用的,比finalize()方法更灵活
     */
    private static void simpleUsePhantomRefDemo() throws InterruptedException {
        Object obj = new Object();
        System.out.println("obj:" + obj);
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        //不同的Refence子类，实现不同，效果也不同
//        SoftReference<Object> reference = new SoftReference<>(obj, refQueue);
//        WeakReference<Object> reference = new WeakReference<>(obj, refQueue);
        PhantomReference<Object> reference = new PhantomReference<>(obj, refQueue);

        //SoftReference、WeakReference 都能拿到referent，PhantomReference永远返回null
        System.out.println("1:" + reference.get());
        //没有被jvm标记为需要回收的对象时，都是返回null
        System.out.println("2:" + refQueue.poll());
        //将obj这个引用置空，不指向任何对象
        obj = null;
        // 通知JVM的gc进行垃圾回收
        System.gc();
        //SoftReference 如果内存情况ok还是能正常返回一开始obj指向的那个对象,WeakReference、PhantomReference返回null
        System.out.println("3:" + reference.get());
        // 当GC发现了虚引用，GC会将reference插入进我们之前创建时传入的refQueue队列
        // 注意，此时phantomRef对象，并没有被GC回收，在我们显式地调用refQueue.poll返回phantomRef之后
        // 当GC第二次发现虚引用，而此时JVM将phantomRef插入到refQueue会插入失败，此时GC才会对phantomRef对象进行回收
        Thread.sleep(200);
        // SoftReference因为没有回收reference，所以在refQueue队列中poll出来是null;弱引用poll出来reference对象
        Reference<?> pollObj = refQueue.poll();
        System.out.println("4:Reference=" + pollObj);
        if (pollObj != null) {
            System.out.println("Reference.get=" + pollObj.get());
        }
        // SoftReference因为没有回收reference，所以get出来时还是返回一开始obj指向的那个对象;WeakReference、PhantomReference返回null
        System.out.println("5:" + reference.get());
        if (null != pollObj) {
            // 进行资源回收的操作
        }
    }
}
