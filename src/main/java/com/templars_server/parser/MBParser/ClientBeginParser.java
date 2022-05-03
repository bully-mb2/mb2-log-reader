package com.templars_server.parser.MBParser;

import generated.ClientBeginEvent;

import java.util.regex.Matcher;

public class ClientBeginParser extends MBEvent<ClientBeginEvent> {

    public ClientBeginParser() {
        super("ClientBegin: ([0-9]{1,2})");
    }

    @Override
    protected ClientBeginEvent parseEvent(Matcher matcher) {
        try {
            ClientBeginEvent clientBeginEvent = new ClientBeginEvent();
            clientBeginEvent.setSlot(Integer.parseInt(matcher.group(1)));
            return clientBeginEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
