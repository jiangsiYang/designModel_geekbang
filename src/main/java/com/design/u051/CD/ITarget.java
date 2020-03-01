package com.design.u051.CD;

// 使用适配器模式进行重构(这是我们重新定义的接口)
public interface ITarget {
    void function1();

    void function2();

    void fucntion3(ParamsWrapperDefinition paramsWrapper);

    void function4();
}
