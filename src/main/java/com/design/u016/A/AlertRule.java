package com.design.u016.A;

/**
 * AlertRule 存储告警规则，可以自由设置。
 */
public class AlertRule {
    public MatchedRule getMatchedRule(String api) {
        return new MatchedRule();
    }


    public static class MatchedRule {
        int maxErrorCount;
        int maxTps;
        int maxTimeoutTps;

        public int getMaxErrorCount() {
            return maxErrorCount;
        }

        public void setMaxErrorCount(int maxErrorCount) {
            this.maxErrorCount = maxErrorCount;
        }

        public int getMaxTps() {
            return maxTps;
        }

        public void setMaxTps(int maxTps) {
            this.maxTps = maxTps;
        }

        public int getMaxTimeoutTps() {
            return maxTimeoutTps;
        }

        public void setMaxTimeoutTps(int maxTimeoutTps) {
            this.maxTimeoutTps = maxTimeoutTps;
        }
    }

}
