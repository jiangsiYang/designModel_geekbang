package com.design.u040;

import com.google.common.annotations.VisibleForTesting;

import javax.xml.crypto.Data;
import java.util.*;

public class EmailReporter extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;
    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<String>();

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函数
    public EmailReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new EmailViewer());
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public EmailReporter(IMetricsStorage metricsStorage, Aggregator aggregator, StatViewer statViewer) {
        super(metricsStorage, aggregator, statViewer);
        this.emailSender = new EmailSender(/*省略参数*/);
    }


    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    public void startDailyReport() {
        //获取当前时间的下一天的 0 点时间
        Date firstTime = trimTimeFieldsToZeroOfNextDay(new Date());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                doStatAndReport(startTimeInMillis, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }

    // 设置成protected而非private是为了方便写单元测试
    @VisibleForTesting
    protected Date trimTimeFieldsToZeroOfNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); //重新设置时间,为什么要重新设置呢?完全是为了提高代码的可测试性
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
