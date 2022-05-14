package com.design.u050.beverage;

public class Demo {
    public static void main(String args[]) {
        Beverage houseBlend = new HouseBlend();
        System.out.println(houseBlend.getDescription() + "$" + houseBlend.cost());
        //一杯饮料可以动态添加各种调料，这就是装饰者模式的强悍之处
        Beverage houseBlend2 = new HouseBlend();
        houseBlend2 = new Mocha(houseBlend2);
        houseBlend2 = new Whip(houseBlend2);
        System.out.println(houseBlend2.getDescription() + "$" + houseBlend2.cost());

    }
}
