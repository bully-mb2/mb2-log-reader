package com.templars_server.parser.MBParser;

import com.templars_server.parser.ParsableEvent;
import generated.ClientBeginEvent;

import java.util.regex.Matcher;

public class ClientBeginParser extends ParsableEvent<ClientBeginEvent> {

    public ClientBeginParser(String regex) {
        super(regex);
    }

    @Override
    protected ClientBeginEvent parseEvent(Matcher matcher) {
        return null;
    }

}
