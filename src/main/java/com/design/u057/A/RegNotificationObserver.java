package com.design.u057.A;

/**
 * 异步非阻塞观察者模式的简易实现
 * 第一种是：在每个 handleRegSuccess() 函数中创建一个新的线程执行代码逻辑；
 */
public class RegNotificationObserver implements RegObserver {
    private NotificationService notificationService;

    @Override
    public void handleRegSuccess(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notificationService.sendInboxMessage(userId, "Welcome...");
            }
        });
        thread.start();
    }
}
