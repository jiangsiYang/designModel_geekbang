package com.design.u044.G;

import com.design.u044.IRuleConfigParser;
import com.design.u044.XmlRuleConfigParser;

public class XmlConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new XmlRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new XmlSystemConfigParser();
    }
}
