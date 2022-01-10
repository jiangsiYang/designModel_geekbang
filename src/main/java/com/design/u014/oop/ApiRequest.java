package com.design.u014.oop;

/**
 * Url 类相关的功能点有两个：
 * 将 token、AppID、时间戳拼接到 URL 中，形成新的 URL；
 * 解析 URL，得到 token、AppID、时间戳等信息。
 */

import org.apache.logging.log4j.util.Strings;

/**
 * 疑问1：为何appId这些属性是放在这个类里而不是AuthToken类里呢
 * 疑问2：为何有appId属性，而没有secreKey属性呢？
 * 疑问3:为何只要解析url的方法，没有组装fullUrl的方法呢？
 */
public class ApiRequest {


    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    /**
     * 解析url得到ApiRequest对象
     * 这个解析想写得优雅点，目前没啥想法
     *
     * @param url
     * @return
     */
    public static ApiRequest createFromFullUrl(String url) {
        if (Strings.isEmpty(url)) {
            return null;
        }
        //获取url后面的参数
        String param = url.substring(url.indexOf("?"), url.length());
        if (Strings.isEmpty(param)) {
            return null;
        }

        return new ApiRequest("baseUrl", "token", "appid", System.currentTimeMillis());
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
