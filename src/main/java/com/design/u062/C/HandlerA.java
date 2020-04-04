package com.design.u062.C;

public class HandlerA implements IHandler {
    @Override
    public boolean handle() {
        //false 表示不处理，继续向下传递请求
        boolean handled = false;
        System.out.println("传递到HandlerA了");
        //...
        return handled;
    }
}
