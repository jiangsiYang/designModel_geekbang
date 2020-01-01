package com.design.u014.procedureOriented.service;

import com.design.u014.procedureOriented.pojo.AuthToken;

import java.security.MessageDigest;

/**
 * 模拟这个以前的面向过程思维的编写 接口鉴权相关代码
 */
public class AuthTokenUtil {

    /**
     * 返回加密后的url
     *
     * @param authToken
     * @return
     */
    public static String encryptUrl(AuthToken authToken) {

        Long currentTime = System.currentTimeMillis();
        //加密字符串
        String encryptStr = encryptToken(authToken);

        return authToken.getUrl() + "?appId=" + authToken.getAppId() + "&timeStamp=" + currentTime + "&encryptStr=" + encryptStr;

    }

    //加密字符串
    public static String encryptToken(AuthToken authToken) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encryptStr = messageDigest.digest((authToken.getAppId() + authToken.getSecreKey() + authToken.getTimeStamp()).getBytes());
            return encryptStr.toString();
        } catch (Exception e) {
            return null;
        }
    }


    //将url解析成AuthToken对象
    public static AuthToken getAuthTokenFromUrl(String url) {
        return new AuthToken();
    }

    //判断token是否失效
    public static boolean tokenExpire(Long time) {
        return System.currentTimeMillis() + 60 * 1000 > time;
    }

}
