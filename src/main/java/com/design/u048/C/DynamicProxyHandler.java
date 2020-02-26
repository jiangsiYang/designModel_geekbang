package com.design.u048.C;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 这是动态代理的另一个demo
 */
public class DynamicProxyHandler implements InvocationHandler {
    private Object tar;

    public Object bind(Object tar) {
        this.tar = tar;
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(), tar.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("在这里进行非业务类处理");
        result = method.invoke(tar, args);
        return result;
    }
}
