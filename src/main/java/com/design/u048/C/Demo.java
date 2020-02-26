package com.design.u048.C;


/**
 * MetricsCollectorProxy使用举例
 */
public class Demo {
    public static void main(String[] args) {
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        UserController userController = (UserController) proxy.createProxy(new UserController());
        userController.login("13607841111", "123456");
    }
}