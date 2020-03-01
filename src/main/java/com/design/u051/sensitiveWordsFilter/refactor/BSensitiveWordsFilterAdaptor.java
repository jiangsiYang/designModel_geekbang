package com.design.u051.sensitiveWordsFilter.refactor;

import com.design.u051.sensitiveWordsFilter.ASensitiveWordsFilter;
import com.design.u051.sensitiveWordsFilter.BSensitiveWordsFilter;

public class BSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {
    private BSensitiveWordsFilter filter;

    @Override
    public String filter(String text) {
        String maskedText = filter.filter(text);
        return maskedText;
    }
}
