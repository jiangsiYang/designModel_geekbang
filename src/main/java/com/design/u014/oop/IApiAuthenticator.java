package com.design.u014.oop;

/**
 * 接口鉴权并不是一个独立运行的系统，而是一个集成在系统上运行的组件，所以，我们封装所有的实现细节，设计了一个最顶层的 ApiAuthenticator 接口类，
 * 暴露一组给外部调用者使用的 API 接口，作为触发执行鉴权逻辑的入口。
 * 这个可以理解为component层或者service层都可以吧？
 */

public interface IApiAuthenticator {
    /**
     * 根据url来解析成ApiRequest再验证这个url是否验证通过？
     * @param url
     */
    void auth(String url);

    /**
     * 直接用ApiRequest验证是否通过接口校验？
     * @param apiRequest
     */
    void auth(ApiRequest apiRequest);
}
