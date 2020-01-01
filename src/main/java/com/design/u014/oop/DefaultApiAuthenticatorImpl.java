package com.design.u014.oop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultApiAuthenticatorImpl implements IApiAuthenticator {
    @Autowired
    private ICredentialStorage iCredentialStorage;

    /**
     * 根据url来解析成ApiRequest再验证这个url是否验证通过
     *
     * @param url
     */
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        auth(apiRequest);
    }

    /**
     * 直接用ApiRequest验证是否通过接口校验？
     *
     * @param apiRequest
     */
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        String baseUrl = apiRequest.getBaseUrl();
        long timeStamp = apiRequest.getTimestamp();

        //判断判断token是否失效
        AuthToken authToken = new AuthToken(token, timeStamp);
        if (authToken.isExpired()) {
            throw new RuntimeException("token is expired");
        }

        //从数据库中获取appid 对应的密钥
        String password = iCredentialStorage.getPasswordByAppId(appId);

        //将传过来的参数再次与密钥加密得到加密串并与传过来的加密串进行对比
        AuthToken serverAuthToken = AuthToken.generate(baseUrl, appId, password, timeStamp);
        if (!serverAuthToken.match(authToken)) {
            throw new RuntimeException("Token verfication failed");
        }
    }
}
