package com.design.u050.beverage;

/**
 * 这是一个饮料的抽象类，不同的饮料类型有不同的价格
 */
public abstract class Beverage {
    String description = "Unknown beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

