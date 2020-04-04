package com.design.u062.G;

import java.util.ArrayList;
import java.util.List;

public class SensitiveWordFilterChain {
    private List<SensitiveWordFilter> filters = new ArrayList<>();

    public void addFilter(SensitiveWordFilter filter) {
        this.filters.add(filter);
    }

    // return true if content doesn't contain sensitive words.
    public void filter(Content content) {
        for (SensitiveWordFilter filter : filters) {
            filter.doFilter(content);
        }
    }
}
