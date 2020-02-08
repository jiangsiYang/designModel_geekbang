package com.design.u039;

import com.google.gson.Gson;
import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 1.根据给定的时间区间，从数据库中拉取数据；
 * 2.根据原始数据，计算得到统计数据；
 * 3.将统计数据显示到终端（命令行或邮件）；
 * 4.定时触发以上 3 个过程的执行。
 */

/**
 * ConsoleReporter 类相当于一个上帝类，定时根据给定的时间区间，从数据库中取出数据，借助 Aggregator 类完成统计工作，并将统计结果输出到命令行。
 * 这里的策略是将1、3、4逻辑放到ConsoleReporter类中，把第2个逻辑放到Aggregator类中
 */
public class ConsoleReporter {
    private IMetricsStorage metricsStorage;
    private ScheduledExecutorService executor;
    private Aggregator aggregator;
    private StatViewer viewer;

    public ConsoleReporter(IMetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * @param periodInSeconds
     * @param durationInSeconds 统计多少秒之内的数据
     */
    public void startRepeatedReport(long periodInSeconds, final long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, durationInSeconds);
                // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
                viewer.output(stats, startTimeInMillis, endTimeInMillis);
            }
        }, 0, periodInSeconds, TimeUnit.SECONDS);


    }
}
