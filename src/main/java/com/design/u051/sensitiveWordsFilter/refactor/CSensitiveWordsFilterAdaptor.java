package com.design.u051.sensitiveWordsFilter.refactor;

import com.design.u051.sensitiveWordsFilter.ASensitiveWordsFilter;
import com.design.u051.sensitiveWordsFilter.CSensitiveWordsFilter;

public class CSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {
    private CSensitiveWordsFilter filter;

    @Override
    public String filter(String text) {
        String maskedText = filter.filter(text, "***");
        return maskedText;
    }
}
