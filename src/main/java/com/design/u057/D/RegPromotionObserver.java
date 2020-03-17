package com.design.u057.D;

import com.design.u056.register.PromotionService;

public class RegPromotionObserver implements RegObserver {
    private PromotionService promotionService; // 依赖注入

    @Override
    @Subscribe
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}
