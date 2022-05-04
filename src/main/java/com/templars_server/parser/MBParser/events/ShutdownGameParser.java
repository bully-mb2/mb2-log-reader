package com.templars_server.parser.MBParser.events;

import generated.ShutdownGameEvent;

import java.util.regex.Matcher;

public class ShutdownGameParser extends MBEventParser<ShutdownGameEvent> {

    public ShutdownGameParser() {
        super("^ShutdownGame:$");
    }

    @Override
    protected ShutdownGameEvent parseEvent(Matcher matcher) {
        return new ShutdownGameEvent();
    }

}
