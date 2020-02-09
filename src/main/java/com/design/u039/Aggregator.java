package com.design.u039;

import java.util.*;

/**
 * 负责将原始数据计算得到统计数据
 */
public class Aggregator {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInSeconds) {
        Map<String, RequestStat> stats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            // 第2个代码逻辑：根据原始数据，计算得到统计数据；
            RequestStat requestStat = this.doAggregate(entry.getValue(), durationInSeconds);
            stats.put(entry.getKey(), requestStat);
        }
        return stats;
    }

    /**
     * 这个方法的重构还是有点东西的，对比u026的未重构前的doAggregate
     *
     * @param requestInfos
     * @param durationInMillis 多少时间内，比如10分钟内
     * @return
     */
    public RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        //为啥还要循环再封装成List<Double> respTimes呢？直接把List<RequestInfo> requestInfos 传进各个函数不就好了？
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            respTimes.add(requestInfo.getResponseTime());
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis / 1000));
        return requestStat;
    }


    // 以下的函数的代码实现均省略...
    private double max(List<Double> dataset) {
        return 0;
    }

    private double min(List<Double> dataset) {
        return 0;
    }

    private double avg(List<Double> dataset) {
        return 0;
    }

    private double tps(int count, double duration) {
        return 0;
    }

    private double percentile999(List<Double> dataset) {
        return 0;
    }

    private double percentile99(List<Double> dataset) {
        return 0;
    }

    private double percentile(List<Double> dataset, double ratio) {
        return 0;
    }
}
