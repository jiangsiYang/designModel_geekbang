package com.design.u044.D;

import com.design.u044.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂的第二种实现方法.A的版本每次都要创建一个新的对象，实际上，如果 parser 可以复用，为了节省内存和对象创建的时间，
 * 我们可以将 parser 事先创建好缓存起来。当调用 createParser() 函数的时候，我们从缓存中取出 parser 对象直接使用。
 */
public class RuleConfigParserFactoryB {
    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml", new XmlRuleConfigParser());
        cachedParsers.put("yaml", new YamlRuleConfigParser());
        cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }


    public static IRuleConfigParser createParser(String configFormat) {
        if (configFormat == null || configFormat.isEmpty()) {
            return null;//返回null还是IllegalArgumentException全凭你自己说了算 }
        }
        return cachedParsers.get(configFormat.toLowerCase());
    }
}
