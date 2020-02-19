package com.design.u045;

import java.io.InputStream;
import java.util.List;

/**
 * 配置文件解析主要包含 BeanConfigParser 接口和 XmlBeanConfigParser 实现类，负责将配置文件解析为 BeanDefinition 结构，以便 BeansFactory 根据这个结构来创建对象。
 */
public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream inputStream);

    List<BeanDefinition> parse(String configContent);
}
