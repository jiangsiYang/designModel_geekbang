package com.design.u057.A;

import com.design.u056.register.PromotionService;

/**
 * 异步非阻塞观察者模式的简易实现
 * 第一种是：在每个 handleRegSuccess() 函数中创建一个新的线程执行代码逻辑；
 */
public class RegPromotionObserver implements RegObserver {
    private PromotionService promotionService; // 依赖注入

    @Override
    public void handleRegSuccess(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                promotionService.issueNewUserExperienceCash(userId);
            }
        });
        thread.start();
    }
}
