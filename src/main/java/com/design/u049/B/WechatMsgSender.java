package com.design.u049.B;

import java.util.List;

public class WechatMsgSender implements MsgSender {
    private List<String> wechatIds;

    public WechatMsgSender(List<String> wechatIds) {
        this.wechatIds = wechatIds;
    }

    @Override
    public void send(String message) {

    }
}
