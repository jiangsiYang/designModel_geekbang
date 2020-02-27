package com.design.u048.C;


/**
 * MetricsCollectorProxy使用举例
 */
public class Demo {
    public static void main(String[] args) {
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
//        UserController userController = (UserController) proxy.createProxy(new UserController());
        //jdk动态代理的原始类必须要实现一个接口
        IUserController userController = (IUserController) proxy.createProxy(new UserController());
        userController.login("13607841111", "123456");
    }
}