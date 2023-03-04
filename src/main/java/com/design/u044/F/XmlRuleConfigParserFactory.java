package com.design.u044.F;

import com.design.u044.IRuleConfigParser;
import com.design.u044.XmlRuleConfigParser;

public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser(String configFormat) {
        return new XmlRuleConfigParser();
    }
}
