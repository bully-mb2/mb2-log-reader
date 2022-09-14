package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ServerInitializationEvent;

import java.util.regex.Matcher;

public class ServerInitializationParser extends MBEventParser<ServerInitializationEvent> {

    public ServerInitializationParser() {
        super("^------ Server Initialization ------$");
    }

    @Override
    protected ServerInitializationEvent parseEvent(Matcher matcher) {
        return new ServerInitializationEvent();
    }

}
