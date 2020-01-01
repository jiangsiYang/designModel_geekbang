package com.design.u014.oop;

/**
 * CredentialStorage 类相关的功能点有一个：从存储中取出 AppID 和对应的密码。
 * 为了做到抽象封装具体的存储方式，我们将 CredentialStorage 设计成了接口，基于接口而非具体的实现编程。
 * 这层也应该是属于component层
 */

public interface ICredentialStorage {
    String getPasswordByAppId(String appId);
}
