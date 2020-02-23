package com.design.u047;

public class SearchWord {
    private long lastUpdateTime;
    private String keyword;
    private int count;

    public SearchWord(String keyword, int count, long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        this.keyword = keyword;
        this.count = count;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
