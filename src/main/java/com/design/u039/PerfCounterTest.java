package com.design.u039;

public class PerfCounterTest {
    public static void main(String args[]) {
        IMetricsStorage metricsStorage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        // 定时触发统计并将结果显示到终端
        StatViewer consoleStatViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(metricsStorage, aggregator, consoleStatViewer);
        consoleReporter.startRepeatedReport(60, 60);

        // 定时触发统计并将结果输出到邮件
        StatViewer emailViewer = new EmailViewer();
        ((EmailViewer) emailViewer).addToAddress("wangzheng@xzg.com");
        EmailReporter emailReporter = new EmailReporter(metricsStorage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        //收集接口访问数据
        MetricsCollector collector = new MetricsCollector(metricsStorage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
