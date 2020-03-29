package com.design.u060.B;

/**
 * 在这个例子中，我们没有使用策略模式，而是将策略的定义、创建、使用直接耦合在一起。
 * 如何来移除掉分支判断逻辑呢？那策略模式就派上用场了。参考C
 */
public class OrderService {
    public double discount(Order order) {
        double discount = 0.0;
        OrderType type = order.getType();
        if (type.equals(OrderType.NORMAL)) { // 普通订单
            //...省略折扣计算算法代码
        } else if (type.equals(OrderType.GROUPON)) { // 团购订单
            //...省略折扣计算算法代码
        } else if (type.equals(OrderType.PROMOTION)) { // 促销订单
            //...省略折扣计算算法代码
        }
        return discount;
    }
}
