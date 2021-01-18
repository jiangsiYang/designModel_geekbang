package com.design.u014.oop;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * AuthToken 类相关的功能点有四个：
 * 把 URL、AppID、密码、时间戳拼接为一个字符串；
 * 对字符串通过加密算法加密生成 token；
 * 根据时间戳判断 token 是否过期失效；
 * 验证两个 token 是否匹配。
 */

public class AuthToken {
    //token1小时过期
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 1000 * 60 * 60;

    /**
     * 并不是所有出现的名词都被定义为类的属性，比如 URL、AppID、密码、时间戳这几个名词，我们把它作为了方法的参数
     */

    /**
     * 拼接后的token
     */
    private String token;

    /**
     * 既然AppID、密码都不能做属性，为何这里有个时间戳？
     * 拼接token时的时间戳
     */
    private long createTime;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }


    public String getToken() {
        return token;
    }


    /**
     * 把 URL、AppID、密码、时间戳拼接为一个字符串；
     *
     * @param baseUrl
     * @param appId
     * @param secreKey
     * @param createtTime
     * @return
     */
    public static AuthToken generate(String baseUrl, String appId, String secreKey, long createtTime) {
        String token = encryptToken(baseUrl + appId + createtTime, secreKey);
        AuthToken authToken = new AuthToken(token, createtTime);
        return authToken;
    }

    /**
     * 这一步使用MD5加密的方法也要放在AuthToken类里吗？还是要专门放到MD5加密类里？
     *
     * @param str
     * @param password
     * @return
     */
    public static String encryptToken(String str, String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encryptStr = messageDigest.digest((str + password).getBytes());
            return encryptStr.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 另一种加密方式
     *
     * @param str
     * @param password
     * @return
     */
    private static String hmacSha1(String str, String password) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = password.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(str.getBytes());

            byte[] result = Base64.getEncoder().encode(rawHmac);

            //  Covert array of Hex bytes to a String
            return new String(result, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 若token的时间戳已经超过1小时了，即为过期
     *
     * @return
     */
    public boolean isExpired() {
        return System.currentTimeMillis() > this.createTime + DEFAULT_EXPIRED_TIME_INTERVAL;
    }

    /**
     * @param authToken
     * @return
     */
    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.getToken());
    }


}
