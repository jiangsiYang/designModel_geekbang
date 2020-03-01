package com.design.u051.sensitiveWordsFilter.refactor;

import com.design.u051.sensitiveWordsFilter.ASensitiveWordsFilter;

public class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {
    private ASensitiveWordsFilter filter;

    @Override
    public String filter(String text) {
        String maskedText = filter.filterSexyWords(text);
        maskedText = filter.filterPoliticalWords(maskedText);
        return maskedText;
    }
}
