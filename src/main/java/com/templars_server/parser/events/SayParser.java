package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ChatChannel;
import com.templars_server.mb2_log_reader.schema.SayEvent;

import java.util.regex.Matcher;

public class SayParser extends MBEventParser<SayEvent> {

    public SayParser() {
        super("^([0-9]{1,2}): (say|sayteam): (.*?)\u0019?: \"(.*)\"$");
    }

    @Override
    protected SayEvent parseEvent(Matcher matcher) {
        try {
            SayEvent sayEvent = new SayEvent();

            sayEvent.setSlot(Integer.parseInt(matcher.group(1)));
            sayEvent.setChatChannel(ChatChannel.fromValue(matcher.group(2)));
            sayEvent.setName(matcher.group(3));
            sayEvent.setMessage(matcher.group(4));

            return sayEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
