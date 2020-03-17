package com.design.u057.D;

public class RegNotificationObserver implements RegObserver {
    private NotificationService notificationService;

    @Override
    @Subscribe
    public void handleRegSuccess(long userId) {
        notificationService.sendInboxMessage(userId, "Welcome...");
    }
}
