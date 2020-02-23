package com.thread;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 配合java线程状态食用的test，后面可能删掉或移出这个项目
 */
public class ThreadStatusTest {


    public static void main(String args[]) {

        Object o = new Object();

        class Counter {
            int counter;

            public synchronized void increase() {
                counter++;
                try {
                    System.out.println(Thread.currentThread().getName() + "开始睡觉");
//                    Thread.sleep(3000);
                    wait();
                    System.out.println(Thread.currentThread().getName() + "起床");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Counter c = new Counter();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                c.increase();
            }
        }, "t1线程");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                c.increase();
            }
        }, "t2线程");

        t2.start();
        try {
            Thread.sleep(100); // 确保 t2 run已经得到执行
            selectThreadStatus();
            System.out.println(t1.getState());
            System.out.println(t2.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<String> selectThreadStatus() {
        List<String> list = new ArrayList<>();
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        if (map != null && map.size() > 0) {
            for (Map.Entry<Thread, StackTraceElement[]> stackTrace : map.entrySet()) {
                Thread thread = stackTrace.getKey();
                StackTraceElement[] stack = stackTrace.getValue();
                //当前查询的线程就不用查询出来了
                if (thread.equals(Thread.currentThread())) {
                    continue;
                }
                StringBuilder info = new StringBuilder();
                info.append("线程：" + thread.getName());
                for (StackTraceElement element : stack) {
                    info.append(element);
                    info.append("\r\n");
                }
                System.out.println(info.toString());
            }
        }
        return list;
    }

}

