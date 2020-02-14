package com.design.u040;

import java.util.List;
import java.util.Map;

/**
 * 将 ConsoleReporter 和 EmailReporter 中的相同代码逻辑，提取到父类 ScheduledReporter 中，以解决代码重复问题
 * 其实群接龙活动详情的service层就有点类似这个
 */
public abstract class ScheduledReporter {
    //因为涉及到子类，这里不能用private了
    protected IMetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected StatViewer viewer;

    public ScheduledReporter(IMetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    protected void doStatAndReport(long startTimeInMillis, long endTimeInMillis) {
        //这句很骚
        long durationInSeconds = endTimeInMillis - startTimeInMillis;
        Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        // 第2个代码逻辑：根据原始数据，计算得到统计数据；
        Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, durationInSeconds);
        // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
        viewer.output(stats, startTimeInMillis, endTimeInMillis);
    }
}
