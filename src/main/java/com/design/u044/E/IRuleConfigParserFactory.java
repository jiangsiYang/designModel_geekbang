package com.design.u044.E;

import com.design.u044.IRuleConfigParser;

public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser(String configFormat);
}
