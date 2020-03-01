package com.design.u051.classAdapter;

/**
 * Adaptor 将 Adaptee 转化成一组符合 ITarget 接口定义的接口。
 * // 类适配器: 基于继承
 */
public class Adaptor extends Adaptee implements ITarget {
    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        super.fb();
    }
}
