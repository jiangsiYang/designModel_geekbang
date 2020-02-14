package com.design.u040;

public class EventListener {
    public void saveRequestInfo(RequestInfo requestInfo) {
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
