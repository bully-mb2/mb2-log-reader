package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.SmodEvent;
import java.util.regex.Matcher;

public class SmodSpecialParser extends MBEventParser<SmodEvent> {

    public SmodSpecialParser() {
        super("^Server.*:(?: \\^7TK points of | )(.*?) \\^7(was|were|put on|removed from).*? ?(unmuted|muted|set to|probation) .*?(?:\\^3([0-9]{1,3}) ?)?(?: minute\\(s\\) )?\\^7by Admin \\^3#([0-9]{1,2})$");
    }

    @Override
    protected SmodEvent parseEvent(Matcher matcher) {
        try {
            SmodEvent smodEvent = new SmodEvent();

            smodEvent.setAdminSlot(-1);
            smodEvent.setAdminId(Integer.parseInt(matcher.group(5)));
            smodEvent.setCommand(interpretCommandFromAction(matcher.group(2), matcher.group(3)));
            smodEvent.setArgs(matcher.group(4));
            smodEvent.setTargetSlot(-1);
            smodEvent.setTargetName(matcher.group(1));

            return smodEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String interpretCommandFromAction(String verb, String action) {
        switch (action) {
            case "muted":
                return "mute";
            case "unmuted":
                return "unmute";
            case "set to":
                return "settk";
            case "probation":
                return verb.equals("removed from") ? "unmarktk": "marktk";
        }

        return null;
    }
}
