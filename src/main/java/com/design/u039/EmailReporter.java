package com.design.u039;

import java.util.*;

public class EmailReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;
    private IMetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer statViewer;
    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<String>();


    public EmailReporter(IMetricsStorage metricsStorage, Aggregator aggregator, StatViewer statViewer) {
        this.metricsStorage = metricsStorage;
        this.statViewer = statViewer;
        this.aggregator = aggregator;
        this.emailSender = new EmailSender(/*省略参数*/);
    }


    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, DAY_HOURS_IN_SECONDS);
                // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
                statViewer.output(stats, startTimeInMillis, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }
}
