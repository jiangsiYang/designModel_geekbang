package com.design.u046;


import org.apache.commons.lang3.StringUtils;

/**
 * 使用构造函数+set函数初始化对象
 * 缺点：1、如果参数存在必填项，那么这些必填项只能通过构造函数进行强制必填，如果必填项很多，又会面临构造函数参数列表过长、容易出错的问题。
 * 2、如果参数有约束条件，比如参数之间有依赖关系，设置了A属性就必须要设置B、C属性；D的值一定要小于E和F的值，这些校验逻辑将无处安放。
 * 3、如果我们希望创建后的对象是不可变对象，创建完之后不能再修改内部的属性值，那么我们就不能暴露set()方法。
 * 解决方案：建造者模式（Builder模式），参考ResourcePoolConfigC
 */
public class ResourcePoolConfigB {
    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;

    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;

    public ResourcePoolConfigB(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name should not be empty.");
        }
        this.name = name;
    }

    public void setMaxTotal(int maxTotal) {
        if (maxTotal <= 0) {
            throw new IllegalArgumentException("maxTotal should be positive.");
        }
        this.maxTotal = maxTotal;
    }

    public void setMaxIdle(int maxIdle) {
        if (maxIdle < 0) {
            throw new IllegalArgumentException("maxIdle should not be negative.");
        }
        this.maxIdle = maxIdle;
    }

    public void setMinIdle(int minIdle) {
        if (minIdle < 0) {
            throw new IllegalArgumentException("minIdle should not be negative.");
        }
        this.minIdle = minIdle;
    }
    //...省略getter方法...

    public static void main(String args[]) {
    }
}
