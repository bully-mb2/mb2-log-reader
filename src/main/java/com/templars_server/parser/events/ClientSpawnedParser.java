package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ClientSpawnedEvent;

import java.util.regex.Matcher;

public class ClientSpawnedParser extends MBEventParser<ClientSpawnedEvent> {

    public ClientSpawnedParser() {
        super("^Player ([0-9]{1,2}) spawned with userinfo: (.*)$");
    }

    @Override
    protected ClientSpawnedEvent parseEvent(Matcher matcher) {
        ClientSpawnedEvent clientSpawnedEvent = new ClientSpawnedEvent();
        clientSpawnedEvent.setSlot(Integer.parseInt(matcher.group(1)));

        InfoMap userinfo = parseInfoMap(matcher.group(2));
        try {
            String ip = userinfo.getString("ip");
            if (ip != null) {
                String[] ipSplit = ip.split(":");
                if (ipSplit.length > 1) {
                    clientSpawnedEvent.setIp(ipSplit[0]);
                    clientSpawnedEvent.setPort(Integer.parseInt(ipSplit[1]));
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }

        clientSpawnedEvent.setTeam(userinfo.getTeam("team"));
        clientSpawnedEvent.setForcePowers(userinfo.getForcePowers("forcepowers"));
        clientSpawnedEvent.setRate(userinfo.getInt("rate"));
        clientSpawnedEvent.setSnaps(userinfo.getInt("snaps"));
        clientSpawnedEvent.setName(userinfo.getString("name"));
        clientSpawnedEvent.setModel(userinfo.getString("model"));
        clientSpawnedEvent.setColor1(userinfo.getInt("color1"));
        clientSpawnedEvent.setColor2(userinfo.getInt("color2"));
        clientSpawnedEvent.setColor3(userinfo.getInt("color3"));
        clientSpawnedEvent.setColor4(userinfo.getInt("color4"));
        clientSpawnedEvent.setHandicap(userinfo.getInt("handicap"));
        clientSpawnedEvent.setSex(userinfo.getGender("sex"));
        clientSpawnedEvent.setCgPredictItems(userinfo.getInt("cg_predictItems"));
        clientSpawnedEvent.setSaber1(userinfo.getString("saber1"));
        clientSpawnedEvent.setSaber2(userinfo.getString("saber2"));
        clientSpawnedEvent.setCharColorRed(userinfo.getInt("char_color_red"));
        clientSpawnedEvent.setCharColorGreen(userinfo.getInt("char_color_green"));
        clientSpawnedEvent.setCharColorBlue(userinfo.getInt("char_color_blue"));
        clientSpawnedEvent.setJp(userinfo.getInt("jp"));
        clientSpawnedEvent.setJaGuid(userinfo.getString("ja_guid"));
        clientSpawnedEvent.setPbIndicator(userinfo.getBoolean("pbindicator"));
        clientSpawnedEvent.setTeamTask(userinfo.getInt("teamtask"));
        clientSpawnedEvent.setTeamOverlay(userinfo.getBoolean("teamoverlay"));

        return clientSpawnedEvent;
    }

}
