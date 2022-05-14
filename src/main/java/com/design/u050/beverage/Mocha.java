package com.design.u050.beverage;

/**
 * 摩卡调料，如果加入了这种调料，价格需要+0.2元
 */
public class Mocha extends CondimentDocorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }

    @Override
    public double cost() {
        return 0.2 + beverage.cost();
    }
}
