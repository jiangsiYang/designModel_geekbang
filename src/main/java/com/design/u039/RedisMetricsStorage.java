package com.design.u039;

import java.util.List;
import java.util.Map;

/**
 * redis的方式实现数据存储
 */
public class RedisMetricsStorage implements IMetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTime, long endTime) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTime, long endTime) {
        return null;
    }
}
