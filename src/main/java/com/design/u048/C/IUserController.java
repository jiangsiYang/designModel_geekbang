package com.design.u048.C;

import com.design.u048.UserVo;

public interface IUserController {
    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);
}
