package com.templars_server.parser.events;

import generated.SendingGameReportEvent;

import java.util.regex.Matcher;

public class SendingGameReportParser extends MBEventParser<SendingGameReportEvent> {

    public SendingGameReportParser() {
        super("^Sending Game Report$");
    }

    @Override
    protected SendingGameReportEvent parseEvent(Matcher matcher) {
        return new SendingGameReportEvent();
    }

}
