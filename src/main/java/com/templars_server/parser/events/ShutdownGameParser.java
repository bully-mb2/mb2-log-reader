package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ShutdownGameEvent;

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
