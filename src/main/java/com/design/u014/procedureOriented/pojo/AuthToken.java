package com.design.u014.procedureOriented.pojo;

import lombok.Data;

@Data
public class AuthToken {
    private String url;
    private String appId = "qjl-design";
    private String secreKey = "adasdawqzvxcvsdfsdfsdfwefwefewfsdfsefsdfdsf";
    private Long timeStamp;

    private String encryptStr;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecreKey() {
        return secreKey;
    }

    public void setSecreKey(String secreKey) {
        this.secreKey = secreKey;
    }


    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEncryptStr() {
        return encryptStr;
    }

    public void setEncryptStr(String encryptStr) {
        this.encryptStr = encryptStr;
    }
}
