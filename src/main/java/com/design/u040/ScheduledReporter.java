package com.design.u040;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将 ConsoleReporter 和 EmailReporter 中的相同代码逻辑，提取到父类 ScheduledReporter 中，以解决代码重复问题
 * 其实群接龙活动详情的service层就有点类似这个
 */
public abstract class ScheduledReporter {
    private static final long MAX_STAT_DURATION_IN_MILLIS = 10 * 60 * 1000; // 10minutes,一次性最多取到内存中的数据量，防止撑爆内存

    //因为涉及到子类，这里不能用private了
    protected IMetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected StatViewer viewer;

    public ScheduledReporter(IMetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }


    /**
     * 当统计的时间间隔较大的时候，需要统计的数据量就会比较大。我们可以将其划分为一些小的时间区间（比如 10 分钟作为一个统计单元），针对每个小的时间区间分别进行统计，
     * 然后将统计得到的结果再进行聚合，得到最终整个时间区间的统计结果。
     *
     * @param startTimeInMillis
     * @param endTimeInMillis
     */
    protected void doStatAndReport(long startTimeInMillis, long endTimeInMillis) {
        Map<String, RequestStat> stats = doStat(startTimeInMillis, endTimeInMillis);
        viewer.output(stats, startTimeInMillis, endTimeInMillis);
    }

    private Map<String, RequestStat> doStat(long startTimeInMillis, long endTimeInMillis) {
        Map<String, List<RequestStat>> segmentStats = new HashMap<>();
        long segmentStartTimeMillis = startTimeInMillis;
        while (segmentStartTimeMillis < endTimeInMillis) {
            long segmentEndTimeMillis = segmentStartTimeMillis + MAX_STAT_DURATION_IN_MILLIS;
            if (segmentEndTimeMillis > endTimeInMillis) {
                segmentEndTimeMillis = endTimeInMillis;
            }
            Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(segmentStartTimeMillis, segmentEndTimeMillis);
            if (requestInfos == null || requestInfos.isEmpty()) {
                continue;
            }
            //这段时间的数据已经统计好了
            Map<String, RequestStat> segmentStat = aggregator.aggregate(requestInfos, segmentEndTimeMillis - segmentStartTimeMillis);
            addStat(segmentStats, segmentStat);
            segmentStartTimeMillis += MAX_STAT_DURATION_IN_MILLIS;
        }
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, RequestStat> aggregatedStats = aggregateStats(segmentStats, durationInMillis);
        return aggregatedStats;
    }

    private void addStat(Map<String, List<RequestStat>> segmentStats, Map<String, RequestStat> segmentStat) {
        for (Map.Entry<String, RequestStat> entry : segmentStat.entrySet()) {
            String apiName = entry.getKey();
            RequestStat stat = entry.getValue();
            List<RequestStat> statList = segmentStats.putIfAbsent(apiName, new ArrayList<>());
            statList.add(stat);
        }
    }

    /**
     * 还要将一段段时间的统计数据再进行一次求出最大数/最小数/平均数等，好像有点蛋疼吧
     *
     * @param segmentStats
     * @param durationInMillis
     * @return
     */
    private Map<String, RequestStat> aggregateStats(Map<String, List<RequestStat>> segmentStats, long durationInMillis) {
        Map<String, RequestStat> aggregatedStats = new HashMap<>();
        for (Map.Entry<String, List<RequestStat>> entry : segmentStats.entrySet()) {
            String apiName = entry.getKey();
            List<RequestStat> apiStats = entry.getValue();
            double maxRespTime = Double.MIN_VALUE;
            double minRespTime = Double.MAX_VALUE;
            long count = 0;
            double sumRespTime = 0;
            for (RequestStat stat : apiStats) {
                if (stat.getMaxResponseTime() > maxRespTime) maxRespTime = stat.getMaxResponseTime();
                if (stat.getMinResponseTime() < minRespTime) minRespTime = stat.getMinResponseTime();
                count += stat.getCount();
                sumRespTime += (stat.getCount() * stat.getAvgResponseTime());
            }
            RequestStat aggregatedStat = new RequestStat();
            aggregatedStat.setMaxResponseTime(maxRespTime);
            aggregatedStat.setMinResponseTime(minRespTime);
            aggregatedStat.setAvgResponseTime(sumRespTime / count);
            aggregatedStat.setCount(count);
            aggregatedStat.setTps(count / durationInMillis * 1000);
            aggregatedStats.put(apiName, aggregatedStat);
        }
        return aggregatedStats;
    }
}
