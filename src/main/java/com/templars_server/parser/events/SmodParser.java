package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.SmodEvent;
import java.util.regex.Matcher;

public class SmodParser extends MBEventParser<SmodEvent> {

    public SmodParser() {
        super("^SMOD command \\((.*)\\) executed by (.*)\\(adminID: ([0-9]{1,2})\\) \\(IP: (.{0,15}):([0-9]*)\\)$");
    }

    @Override
    protected SmodEvent parseEvent(Matcher matcher) {
        try {
            SmodEvent smodEvent = new SmodEvent();

            smodEvent.setAdminSlot(-1);
            smodEvent.setAdminId(Integer.parseInt(matcher.group(3)));
            smodEvent.setAdminName(matcher.group(2));
            smodEvent.setAdminIp(matcher.group(4));
            smodEvent.setAdminPort(Integer.parseInt(matcher.group(5)));
            smodEvent.setCommand(matcher.group(1));
            smodEvent.setTargetSlot(-1);

            return smodEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
