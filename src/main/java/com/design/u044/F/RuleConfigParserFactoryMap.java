package com.design.u044.F;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 为工厂类再创建一个简单工厂，也就是工厂的工厂，用来创建工厂类对象。
 * 因为工厂类只包含方法，不包含成员变量，完全可以复用，
 * 不需要每次都创建新的工厂类对象，所以，简单工厂模式的第二种实现思路更加合适。
 */
public class RuleConfigParserFactoryMap {
    private static final Map<String, IRuleConfigParserFactory> cachedFactories = new HashMap<>();

    static {
        cachedFactories.put("json", new JsonRuleConfigParserFactory());
        cachedFactories.put("xml", new XmlRuleConfigParserFactory());
        cachedFactories.put("yaml", new YamlRuleConfigParserFactory());
        cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
    }

    public static IRuleConfigParserFactory getParserFactory(String type) {
        if (StringUtils.isEmpty(type)) {
            return null;
        }

        return cachedFactories.get(type.toLowerCase());
    }
}
