package com.templars_server.parser.MBParser;

import generated.ShutdownGameEvent;

import java.util.regex.Matcher;

public class ShutdownGameParser extends MBEvent<ShutdownGameEvent> {

    public ShutdownGameParser() {
        super("ShutdownGame");
    }

    @Override
    protected ShutdownGameEvent parseEvent(Matcher matcher) {
        return new ShutdownGameEvent();
    }

}
