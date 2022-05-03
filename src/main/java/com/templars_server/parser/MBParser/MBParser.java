package com.templars_server.parser.MBParser;

import com.templars_server.parser.ParsableEvent;
import com.templars_server.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MBParser implements Parser {

    private static final Logger LOG = LoggerFactory.getLogger(MBParser.class);

    private final List<ParsableEvent> parserList;

    public MBParser() {
        this.parserList = new ArrayList<>();
    }

    @Override
    public void init(Properties properties) {
        parserList.add(new ClientBeginParser());
    }

    @Override
    public Object parseLine(String line) {
        return null;
    }

}
