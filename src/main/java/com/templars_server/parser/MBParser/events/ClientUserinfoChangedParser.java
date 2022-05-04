package com.templars_server.parser.MBParser.events;

import generated.ClientUserinfoChangedEvent;

import java.util.regex.Matcher;

public class ClientUserinfoChangedParser extends MBEventParser<ClientUserinfoChangedEvent> {

    public ClientUserinfoChangedParser() {
        super("^ClientUserinfoChanged: ([0-9]{1,2}) (.*)$");
    }

    @Override
    protected ClientUserinfoChangedEvent parseEvent(Matcher matcher) {
        try {
            ClientUserinfoChangedEvent clientUserinfoChangedEvent = new ClientUserinfoChangedEvent();
            clientUserinfoChangedEvent.setSlot(Integer.parseInt(matcher.group(1)));

            InfoMap userinfo = parseInfoMap(matcher.group(2));
            clientUserinfoChangedEvent.setName(userinfo.getString("n"));
            clientUserinfoChangedEvent.setTeam(userinfo.getTeam("t"));
            clientUserinfoChangedEvent.setModel(userinfo.getString("m"));
            clientUserinfoChangedEvent.setColor1(userinfo.getInt("c1"));
            clientUserinfoChangedEvent.setColor2(userinfo.getInt("c2"));
            clientUserinfoChangedEvent.setSiegeclass(userinfo.getString("sc"));
            clientUserinfoChangedEvent.setSaber1(userinfo.getString("s1"));
            clientUserinfoChangedEvent.setSaber2(userinfo.getString("s2"));
            clientUserinfoChangedEvent.setDesiredTeam(userinfo.getTeam("sdt"));
            clientUserinfoChangedEvent.setModelVariant(userinfo.getInt("v"));
            clientUserinfoChangedEvent.setSaberVariant(userinfo.getInt("s"));
            clientUserinfoChangedEvent.setMbClass(userinfo.getMBClass("mbc"));

            return clientUserinfoChangedEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
