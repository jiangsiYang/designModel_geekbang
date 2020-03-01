package com.design.u051.replaceImplement;

// 在我们的项目中，外部系统A的使用示例
public class Demo {
    private IA a;

    public Demo(IA a) {
        this.a = a;
    }

    public static void main(String args[]) {
        Demo demo = new Demo(new A());

        // 借助BAdaptor，Demo的代码中，调用IA接口的地方都无需改动，
        // 只需要将BAdaptor如下注入到Demo即可。
        Demo demo1 = new Demo(new BAdaptor(new B()));
    }
}
