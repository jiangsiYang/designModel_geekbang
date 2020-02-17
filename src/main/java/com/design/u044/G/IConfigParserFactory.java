package com.design.u044.G;

import com.design.u044.IRuleConfigParser;

/**
 * 在简单工厂和工厂方法中，类只有一种分类方式。比如，在规则配置解析那个例子中，解析器类只会根据配置文件格式（Json、Xml、Yaml……）来分类。
 * 但是，如果类有两种分类方式，比如，我们既可以按照配置文件格式来分类，也可以按照解析的对象（Rule 规则配置还是 System 系统配置）来分类，那就会对应下面这 8 个 parser 类。
 * <p>
 * <p>
 * 针对规则配置的解析器：基于接口IRuleConfigParser
 * JsonRuleConfigParser
 * XmlRuleConfigParser
 * YamlRuleConfigParser
 * PropertiesRuleConfigParser
 * <p>
 * 针对系统配置的解析器：基于接口ISystemConfigParser
 * JsonSystemConfigParser
 * XmlSystemConfigParser
 * YamlSystemConfigParser
 * PropertiesSystemConfigParser
 */

public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();

    ISystemConfigParser createSystemParser(); //此处可以扩展新的parser类型，比如IBizConfigParser
}
