package com.design.u014.procedureOriented.service;

import com.design.u014.procedureOriented.component.AuthTokenComponent;
import com.design.u014.procedureOriented.pojo.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 模拟这个以前的面向过程思维的编写 接口鉴权相关代码
 */
@Service
public class AuthTokenService {

    @Autowired
    private AuthTokenComponent authTokenComponent;

    public void decrypt(String url) {
        try {
            //将url解析成AuthToken对象
            AuthToken token = AuthTokenUtil.getAuthTokenFromUrl(url);
            //判断判断token是否失效
            if (AuthTokenUtil.tokenExpire(token.getTimeStamp())) {
                throw new Exception("过期了");
            }
            //从数据库中获取appid 对应的密钥
            String secreKey = authTokenComponent.getAppSecreKeyFromAppId(token.getAppId());
            token.setSecreKey(secreKey);
            //将传过来的参数再次与密钥加密得到加密串并与传过来的加密串进行对比
            String decrypt = AuthTokenUtil.encryptToken(token);
            if (decrypt.equals(token.getEncryptStr())) {
                System.out.println("合法的token");
            } else {
                System.out.println("违法的token");
            }

        } catch (Exception e) {
            System.out.println("违法的token");
        }
    }
}
