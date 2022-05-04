package com.templars_server.parser.MBParser;

import com.templars_server.parser.MBParser.events.*;
import com.templars_server.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MBParser implements Parser {

    private static final Logger LOG = LoggerFactory.getLogger(MBParser.class);

    private final List<MBEventParser<?>> parserList;

    public MBParser() {
        this.parserList = new ArrayList<>();
    }

    @Override
    public void init(Properties properties) {
        LOG.info("Initializing parsers");
        parserList.add(new ClientBeginParser());
        parserList.add(new ClientConnectParser());
        parserList.add(new ClientDisconnectParser());
        parserList.add(new ClientSpawnedParser());
        parserList.add(new ClientUserinfoChangedParser());
        parserList.add(new InitGameParser());
        parserList.add(new KillParser());
        parserList.add(new ShutdownGameParser());
        LOG.info("Parsers: " + parserList);
    }

    @Override
    public Object parseLine(String line) {
        for (MBEventParser<?> parser : parserList) {
            Object result = parser.parseLine(line);
            if (result != null){
                return result;
            }
        }

        return null;
    }

}
