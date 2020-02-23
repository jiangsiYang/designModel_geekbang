package com.design.u046;

import org.apache.commons.lang3.StringUtils;

public class ConstructorArg {
    private boolean isRef;
    private Class type;
    private Object arg;

    private ConstructorArg(ConstructorArgBuilder builder) {
        this.isRef = builder.isRef;
        this.type = builder.type;
        this.arg = builder.arg;
    }

    public static class ConstructorArgBuilder {
        private boolean isRef;
        private Class type;
        private Object arg;

        public ConstructorArg builder() {
            if (!this.isRef) {
                if (this.arg == null || this.type == null) {
                    throw new IllegalArgumentException("isRef 为false时，需要设置arg和type");
                }
            }
            ConstructorArg constructorArg = new ConstructorArg(this);
            return constructorArg;
        }

        public ConstructorArgBuilder setRef(boolean ref) {
            isRef = ref;
            return this;
        }

        public ConstructorArgBuilder setType(Class type) {
            this.type = type;
            return this;
        }

        public ConstructorArgBuilder setArg(Object arg) {
            if (StringUtils.isEmpty(arg.toString())) {
                throw new IllegalArgumentException("不能为空");
            }
            this.arg = arg;
            return this;
        }
    }
}
