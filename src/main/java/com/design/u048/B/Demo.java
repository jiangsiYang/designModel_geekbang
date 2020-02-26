package com.design.u048.B;


/**
 * UserControllerProxy使用举例
 * 基于代理类继承原始类
 */
public class Demo {
    public static void main(String[] args) {
        UserController userController = new UserControllerProxy();
        userController.login("13607841111","123456");
    }
}