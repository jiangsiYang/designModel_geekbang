package com.design.u048.A;

import com.design.u048.UserVo;

/**
 * 代理类 UserControllerProxy 和原始类 UserController 实现相同的接口 IUserController
 */
public interface IUserController {
    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);
}
