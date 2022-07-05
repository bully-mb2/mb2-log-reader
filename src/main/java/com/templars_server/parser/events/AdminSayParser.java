package com.templars_server.parser.events;

import generated.AdminSayEvent;

import java.util.regex.Matcher;

public class AdminSayParser extends MBEventParser<AdminSayEvent> {

    public AdminSayParser() {
        super("^say: Admin: (.+)$");
    }

    @Override
    protected AdminSayEvent parseEvent(Matcher matcher) {
        AdminSayEvent adminSayEvent = new AdminSayEvent();

        adminSayEvent.setMessage(matcher.group(1));

        return adminSayEvent;
    }

}
