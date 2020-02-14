package com.design.u040;

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
public class ConsoleReporter extends ScheduledReporter {
    private ScheduledExecutorService executor;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public ConsoleReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new ConsoleViewer());
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public ConsoleReporter(IMetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
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
                doStatAndReport(startTimeInMillis, endTimeInMillis);
            }
        }, 0, periodInSeconds, TimeUnit.SECONDS);


    }
}
