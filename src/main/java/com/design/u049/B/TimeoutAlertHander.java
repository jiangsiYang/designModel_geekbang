package com.design.u049.B;


/**
 * // 改动二：添加新的handler
 */
public class TimeoutAlertHander extends AlertHandler {
    public TimeoutAlertHander(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {
        long timeoutTps = apiStatInfo.getTimeoutCount() / apiStatInfo.getDurationOfSeconds();
        if (timeoutTps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTimeoutTps()) {
            notification.notify("...");
        }
    }
}
