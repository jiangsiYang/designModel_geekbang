package com.design.u050.beverage;

public class Whip extends CondimentDocorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ",Whip";
    }

    @Override
    public double cost() {
        return 0.5 + beverage.cost();
    }
}
