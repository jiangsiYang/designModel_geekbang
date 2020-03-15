package com.design.u057.B;

public class RegNotificationObserver implements RegObserver {
    private NotificationService notificationService;

    @Override
    public void handleRegSuccess(long userId) {
        notificationService.sendInboxMessage(userId, "Welcome...");
    }
}
