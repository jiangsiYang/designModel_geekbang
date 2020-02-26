package com.design.u048.A;


import com.design.u048.UserVo;


/**
 * UserController 类只负责业务功能，性能计数器的功能由代理模式来做，这里不再入侵业务类
 * 对比u025入侵业务的做法
 */
public class UserController implements IUserController {

    public UserController() {
    }

    public UserVo login(String telephone, String password) {
        //只做自己的业务
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {
        //只做自己的业务
        return null;
    }
}