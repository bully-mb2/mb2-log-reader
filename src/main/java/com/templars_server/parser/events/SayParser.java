package com.templars_server.parser.events;

import generated.ChatChannel;
import generated.SayEvent;

import java.util.regex.Matcher;

public class SayParser extends MBEventParser<SayEvent> {

    public SayParser() {
        super("^([0-9]{1,2}): (say|sayteam): .*: \"(.*)\"$");
    }

    @Override
    protected SayEvent parseEvent(Matcher matcher) {
        try {
            SayEvent sayEvent = new SayEvent();

            sayEvent.setSlot(Integer.parseInt(matcher.group(1)));
            sayEvent.setChatChannel(ChatChannel.fromValue(matcher.group(2)));
            sayEvent.setMessage(matcher.group(3));

            return sayEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
