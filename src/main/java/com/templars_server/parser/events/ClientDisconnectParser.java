package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ClientDisconnectEvent;

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
