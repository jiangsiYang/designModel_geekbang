package com.design.u057.B;

import com.design.u056.register.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 异步非阻塞观察者模式的简易实现
 * 在 UserController 的 register() 函数中使用线程池来执行每个观察者的 handleRegSuccess() 函数
 */
public class UserController {
    private UserService userService; // 依赖注入
    private List<RegObserver> regObservers = new ArrayList<>();
    private Executor executor;

    public UserController(Executor executor) {
        this.executor = executor;
    }

    // 一次性设置好，之后也不可能动态的修改
    public void setRegObservers(List observers) {
        regObservers.addAll(observers);
    }

    public Long register(String telephone, String password) {
        long userId = userService.register(telephone, password);
        for (RegObserver observer : regObservers) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observer.handleRegSuccess(userId);
                }
            });
        }
        return userId;
    }
}
