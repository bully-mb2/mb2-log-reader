package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.SendingGameReportEvent;

import java.util.regex.Matcher;

public class SendingGameReportParser extends MBEventParser<SendingGameReportEvent> {

    public SendingGameReportParser() {
        super("^(?:Sending|Saving) Game Report$");
    }

    @Override
    protected SendingGameReportEvent parseEvent(Matcher matcher) {
        return new SendingGameReportEvent();
    }

}
