package com.design.u040;

public class EventListener {
    private IMetricsStorage metricsStorage;

    public EventListener(IMetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public void saveRequestInfo(RequestInfo requestInfo) {
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
