package com.design.u049.A;


import java.util.ArrayList;
import java.util.List;

public class Alert {
    private List<AlertHandler> alertHandlers = new ArrayList<>();

    public void addAlertHandler(AlertHandler alertHandler) {
        alertHandlers.add(alertHandler);
    }

    /**
     * 现在，如果我们需要添加一个功能，当每秒钟接口超时请求个数，超过某个预先设置的最大阈值时，我们也要触发告警发送通知。这个时候，我们该如何改动代码呢？
     * 首先重构的工作是：
     * 第一部分是将 check() 函数的多个入参封装成 ApiStatInfo 类；
     * 第二部分是引入 handler 的概念，将 if 判断逻辑分散在各个 handler 中。
     *
     * @param apiStatInfo
     */
    // 改动一：参数封装成新类
    public void check(ApiStatInfo apiStatInfo) {
        //改动二：报警规则封装在各个AlertHandler里，这里只是循环AlertHandler集合去执行对应的报警逻辑
        for (AlertHandler alertHandler : alertHandlers) {
            alertHandler.check(apiStatInfo);
        }
    }
}