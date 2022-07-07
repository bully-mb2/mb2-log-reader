package com.templars_server.parser.events;

import generated.FragLimitHitEvent;

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
