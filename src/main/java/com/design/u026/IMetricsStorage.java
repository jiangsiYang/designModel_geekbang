package com.design.u026;

import java.util.List;
import java.util.Map;

/**
 * 数据存储接口，实现方式可能有内存、redis、数据库、文件等
 */
public interface IMetricsStorage {
    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTime, long endTime);

    Map<String, List<RequestInfo>> getRequestInfos(long startTime, long endTime);
}
