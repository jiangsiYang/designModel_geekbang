package com.design.u040;

import java.util.Map;

/**
 * 第 3 个逻辑：将统计数据显示到终端。我们将这部分逻辑剥离出来，设计成一个接口
 */
public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);
}
