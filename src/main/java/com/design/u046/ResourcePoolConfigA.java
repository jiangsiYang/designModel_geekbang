package com.design.u046;


import org.apache.commons.lang3.StringUtils;

/**
 * 使用构造器传递参数
 * 缺点：在使用构造函数的时候,如果参数非常多，我们就容易搞错各参数的顺序，传递进错误的参数值，导致非常隐蔽的 bug。
 * 尝试解决办法:使用set函数传参，参照ResourcePoolConfigB
 */
public class ResourcePoolConfigA {
    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;

    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;

    //如果参数变为8个、16个时，还用构造器传入就非常糟糕了
    public ResourcePoolConfigA(String name, Integer maxTotal, Integer maxIdle, Integer minIdle) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("name should not be empty.");
        }
        this.name = name;

        if (maxTotal != null) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("maxTotal should be positive.");
            }
            this.maxTotal = maxTotal;
        }

        if (maxIdle != null) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("maxIdle should not be negative.");
            }
            this.maxIdle = maxIdle;
        }

        if (minIdle != null) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("minIdle should not be negative.");
            }
            this.minIdle = minIdle;
        }
    }
    //...省略getter方法...
}
