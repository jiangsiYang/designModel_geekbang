package com.design.u050.beverage;

/**
 * 咖啡的一种类型，纯咖啡仅需0.89元
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
