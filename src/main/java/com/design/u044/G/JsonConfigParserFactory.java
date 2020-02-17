package com.design.u044.G;

import com.design.u044.IRuleConfigParser;
import com.design.u044.JsonRuleConfigParser;

public class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new ISystemConfigParser();
    }
}
