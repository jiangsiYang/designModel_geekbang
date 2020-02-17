package com.design.u044.F;

import com.design.u044.IRuleConfigParser;
import com.design.u044.RuleConfig;

/**
 * 利用工厂方法模式的版本。
 * 当我们新增一种 parser 的时候，只需要新增一个实现了 IRuleConfigParserFactory 接口的 Factory 类即可。所以，工厂方法模式比起简单工厂模式更加符合开闭原则。
 * 但是判断用哪一种工厂，还是要加if else 的判断啊。。
 * 可以为工厂类再创建一个简单工厂，也就是工厂的工厂，用来创建工厂类对象。
 */
public class RuleConfigSourceF {

    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);
        if (parserFactory == null) {
            throw new RuntimeException("Rule config file format is not supported: " + ruleConfigFilePath);
        }

        IRuleConfigParser parser = parserFactory.createParser(ruleConfigFileExtension);

        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}
