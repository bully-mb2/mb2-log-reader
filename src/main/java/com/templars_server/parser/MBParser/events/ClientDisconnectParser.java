package com.templars_server.parser.MBParser.events;

import generated.ClientDisconnectEvent;

import java.util.regex.Matcher;

public class ClientDisconnectParser extends MBEventParser<ClientDisconnectEvent> {

    public ClientDisconnectParser() {
        super("^ClientDisconnect: ([0-9]{1,2})$");
    }

    @Override
    protected ClientDisconnectEvent parseEvent(Matcher matcher) {
        try {
            ClientDisconnectEvent clientDisconnectEvent = new ClientDisconnectEvent();
            clientDisconnectEvent.setSlot(Integer.parseInt(matcher.group(1)));
            return clientDisconnectEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
