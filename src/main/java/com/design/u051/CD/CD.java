package com.design.u051.CD;

/**
 * 假设我们依赖的外部系统在接口设计方面有缺陷（比如包含大量静态方法），引入之后会影响到我们自身代码的可测试性。
 * 为了隔离设计上的缺陷，我们希望对外部系统提供的接口进行二次封装，抽象出更好的接口设计，这个时候就可以使用适配器模式了。
 */

//这个类来自外部sdk，我们无权修改它的代码
public class CD {
    public static void staticFunction1() { //...
    }

    public void uglyNamingFunction2() { //...
    }

    public void tooManyParamsFunction3(int paramA, int paramB) {
        //...
    }

    public void lowPerformanceFunction4() { //...
    }
}
