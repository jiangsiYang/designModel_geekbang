package com.design.u049.B;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicationContext 是一个单例类，负责 Alert 的创建、组装（alertRule 和 notification 的依赖注入）、初始化（添加 handlers）工作
 */
public class ApplicationContext {
    private AlertRule alertRule;
    private Notification normalNotification;
    private Notification urgencyNotification;
    private Notification trivialNotification;
    private Alert alert;

    public void initializeBeans() {
        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
        List<String> telephones = new ArrayList<>();
        normalNotification = new NormalNotification(new TelephoneMsgSender(telephones)); //省略一些初始化代码
        urgencyNotification = new UrgencyNotification(new EmailMsgSender(telephones)); //省略一些初始化代码
        trivialNotification = new TrivialNotification(new WechatMsgSender(telephones)); //省略一些初始化代码
        alert = new Alert();
        //TPS的告警有3种通知方式
        alert.addAlertHandler(new TpsAlertHandler(alertRule, normalNotification));
        alert.addAlertHandler(new TpsAlertHandler(alertRule, urgencyNotification));
        alert.addAlertHandler(new TpsAlertHandler(alertRule, trivialNotification));

        alert.addAlertHandler(new ErrorAlertHandler(alertRule, urgencyNotification));
        // 改动三：注册handler
        alert.addAlertHandler(new TimeoutAlertHander(alertRule, trivialNotification));
    }

    public Alert getAlert() {
        return alert;
    }

    // 饿汉式单例
    private static final ApplicationContext instance = new ApplicationContext();

    private ApplicationContext() {
        initializeBeans();
    }

    public static ApplicationContext getInstance() {
        return instance;
    }

}
