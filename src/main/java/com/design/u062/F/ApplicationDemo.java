package com.design.u062.F;

/**
 * 对于包含敏感词的内容，我们有两种处理方式，一种是直接禁止发布，另一种是给敏感词打马赛克（比如，用 *** 替换敏感词）之后再发布。
 * 第一种处理方式符合 GoF 给出的职责链模式的定义，第二种处理方式是职责链模式的变体。
 * 这里是第一种方式的实现
 */
public class ApplicationDemo {
    public static void main(String[] args) {
        SensitiveWordFilterChain filterChain = new SensitiveWordFilterChain();
        filterChain.addFilter(new AdsWordFilter());
        filterChain.addFilter(new SexyWordFilter());
        filterChain.addFilter(new PoliticalWordFilter());
        boolean legal = filterChain.filter(new Content());
        if (!legal) {
            // 不发表
        } else {
            // 发表
        }
    }
}
