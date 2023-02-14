package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.SmodEvent;
import java.util.regex.Matcher;

public class SmodTargetedParser extends MBEventParser<SmodEvent> {

    public SmodTargetedParser() {
        super("^SMOD command \\((.*)\\) executed by (.*)\\(adminID: ([0-9]{1,2})\\) \\(IP: (.*?):([0-9]*)\\) against ([0-9]{1,2})(?: (.*))? \\([0-9]{1,2} resolved to (.*) \\(IP: (.*):([0-9]*)\\)$");
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
            smodEvent.setArgs(matcher.group(7));
            smodEvent.setTargetSlot(Integer.parseInt(matcher.group(6)));
            smodEvent.setTargetName(matcher.group(8));
            smodEvent.setTargetIp(matcher.group(9));
            smodEvent.setTargetPort(Integer.parseInt(matcher.group(10)));

            return smodEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
