package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.FragLimitHitEvent;

import java.util.regex.Matcher;

public class FragLimitHitParser extends MBEventParser<FragLimitHitEvent> {

    public FragLimitHitParser() {
        super("^Exit: Kill limit hit.$");
    }

    @Override
    protected FragLimitHitEvent parseEvent(Matcher matcher) {
        return new FragLimitHitEvent();
    }

}
