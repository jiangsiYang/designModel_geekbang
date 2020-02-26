package com.design.u048;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.Executors;

/**
 * 数据采集接口
 * 对于性能这一点，落实到具体的代码层面，需要解决两个问题，也是我们之前提到过的，一个是采集和存储要异步来执行，因为存储基于外部存储（比如 Redis），会比较慢，异步存储可以降低对
 * 接口响应时间的影响。引入 Google Guava EventBus 来解决。实际上，我们可以把 EventBus 看作一个“生产者 - 消费者”模型或者“发布 - 订阅”模型，采集的数据先放入内存共享队列中，
 * 另一个线程读取共享队列中的数据，写入到外部存储（比如 Redis）中。
 */
public class MetricsCollector {
    private static final int DEFAULT_STORAGE_THREAD_POOL_SIZE = 20;

    //基于接口而非实现编程
    private IMetricsStorage metricsStorage;
    private EventBus eventBus;

    // 兼顾代码的易用性，新增一个封装了默认依赖的构造函
    public MetricsCollector() {
        this(new RedisMetricsStorage(), DEFAULT_STORAGE_THREAD_POOL_SIZE);
    }

    // 兼顾灵活性和代码的可测试性，这个构造函数继续保留
    public MetricsCollector(IMetricsStorage metricsStorage, int threadNumToSaveData) {
        this.metricsStorage = metricsStorage;
        this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(threadNumToSaveData));
        this.eventBus.register(new EventListener());
    }

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isBlank(requestInfo.getApiName())) {
            return;
        }
        eventBus.post(requestInfo);
    }

    public class EventListener {
        @Subscribe
        public void saveRequestInfo(RequestInfo requestInfo) {
            metricsStorage.saveRequestInfo(requestInfo);
        }
    }
}
