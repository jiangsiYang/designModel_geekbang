package com.design.u014.procedureOriented.component;

import org.springframework.stereotype.Component;

@Component
public class AuthTokenComponent {

    //模拟通过从数据库从 appid获取到对应的secreKey
    public String getAppSecreKeyFromAppId(String appid) {
        return "asdasdasdasdasd";
    }
}
