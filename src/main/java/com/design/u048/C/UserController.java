package com.design.u048.C;


import com.design.u048.UserVo;


/**
 * jdk动态代理一定要实现某个接口，否则会报错ava.lang.ClassCastException: com.sun.proxy.$Proxy2 cannot be cast to com.design.u048.C.UserController
 */
public class UserController implements IUserController {

    public UserController() {
    }

    public UserVo login(String telephone, String password) {
        //只做自己的业务
        return null;
    }

    public UserVo register(String telephone, String password) {
        //只做自己的业务
        return null;
    }
}