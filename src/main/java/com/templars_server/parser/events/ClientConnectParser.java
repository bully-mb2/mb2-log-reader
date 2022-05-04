package com.templars_server.parser.events;

import generated.ClientConnectEvent;

import java.util.regex.Matcher;

public class ClientConnectParser extends MBEventParser<ClientConnectEvent> {

    public ClientConnectParser() {
        super("^ClientConnect: \\((.*)\\) ID: ([0-9]{1,2}) \\(IP: (.*):([0-9]*)\\)$");
    }

    @Override
    protected ClientConnectEvent parseEvent(Matcher matcher) {
        try {
            ClientConnectEvent clientConnectEvent = new ClientConnectEvent();
            clientConnectEvent.setName(matcher.group(1));
            clientConnectEvent.setSlot(Integer.parseInt(matcher.group(2)));
            clientConnectEvent.setIp(matcher.group(3));
            clientConnectEvent.setPort(Integer.parseInt(matcher.group(4)));
            return clientConnectEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
