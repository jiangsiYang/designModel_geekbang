package com.design.u062.B;

public class HandlerA extends Handler {
    @Override
    public boolean doHandler() {
        //false 表示不处理，继续向下传递请求
        boolean handled = false;
        System.out.println("传递到HandlerA了");
        return handled;
    }
}
