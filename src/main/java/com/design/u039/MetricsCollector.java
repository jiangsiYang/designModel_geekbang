package com.design.u039;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据采集接口
 */
public class MetricsCollector {
    //基于接口而非实现编程
    private IMetricsStorage metricsStorage;

    //依赖注入
    public MetricsCollector(IMetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
