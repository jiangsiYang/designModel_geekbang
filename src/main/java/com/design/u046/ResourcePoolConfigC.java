package com.design.u046;


import org.apache.commons.lang3.StringUtils;

/**
 * 建造者模式
 * 优点：避免构造函数的参数列表过长，影响代码的可读性和易用性；参数的必填项能够很好支持；能对参数与参数之间的依赖、约束做成判断；能创建不可变对象，创建后不能再更改对象的内部属性。
 * 缺点：代码重复，属性在类和Builder类中都要定义一遍。
 */
public class ResourcePoolConfigC {
    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;

    //注意，这里的赋值是用builder的值
    private ResourcePoolConfigC(Builder builder) {
        this.name = builder.name;
        this.maxTotal = builder.maxTotal;
        this.maxIdle = builder.maxIdle;
        this.minIdle = builder.minIdle;
    }
    //...省略getter方法...

    //我们将Builder类设计成了ResourcePoolConfig的内部类。
    //我们也可以将Builder类设计成独立的非内部类ResourcePoolConfigBuilder。
    public static class Builder {
        private static final int DEFAULT_MAX_TOTAL = 8;
        private static final int DEFAULT_MAX_IDLE = 8;
        private static final int DEFAULT_MIN_IDLE = 0;

        private String name;
        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        public ResourcePoolConfigC build() {
            // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }
            if (maxIdle > maxTotal) {
                throw new IllegalArgumentException("...");
            }
            if (minIdle > maxTotal || minIdle > maxIdle) {
                throw new IllegalArgumentException("...");
            }

            return new ResourcePoolConfigC(this);
        }

        public Builder setName(String name) {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("...");
            }
            this.name = name;
            return this;
        }

        public Builder setMaxTotal(int maxTotal) {
            if (maxTotal <= 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxTotal = maxTotal;
            return this;
        }

        public Builder setMaxIdle(int maxIdle) {
            if (maxIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.maxIdle = maxIdle;
            return this;
        }

        public Builder setMinIdle(int minIdle) {
            if (minIdle < 0) {
                throw new IllegalArgumentException("...");
            }
            this.minIdle = minIdle;
            return this;
        }
    }

    public static void main(String args[]) {
        // 这段代码会抛出IllegalArgumentException，因为minIdle>maxIdle
        ResourcePoolConfigC config = new ResourcePoolConfigC.Builder()
                .setName("dbconnectionpool")
                .setMaxTotal(16)
                .setMaxIdle(10)
                .setMinIdle(12)
                .build();


    }
}

