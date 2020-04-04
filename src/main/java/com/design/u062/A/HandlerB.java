package com.design.u062.A;

public class HandlerB extends Handler {
    @Override
    public void handle() {
        //false 表示不处理，继续向下传递请求
        boolean handled = false;
        System.out.println("传递到HandlerB了");
        //...
        if (!handled && successor != null) {
            successor.handle();
        }
    }
}
