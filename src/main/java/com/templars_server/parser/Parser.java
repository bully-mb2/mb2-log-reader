package com.templars_server.parser;

import java.util.Properties;

public interface Parser {

    void init(Properties properties);

    Object parseLine(String line);

}
