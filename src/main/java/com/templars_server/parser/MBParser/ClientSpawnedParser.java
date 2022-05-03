package com.templars_server.parser.MBParser;

import generated.ClientSpawnedEvent;
import generated.Gender;
import generated.Team;

import java.util.Map;
import java.util.regex.Matcher;

public class ClientSpawnedParser extends MBEvent<ClientSpawnedEvent> {

    public ClientSpawnedParser() {
        super("^Player ([0-9]{1,2}) spawned with userinfo: (.*)$");
    }

    @Override
    protected ClientSpawnedEvent parseEvent(Matcher matcher) {
        try {
            ClientSpawnedEvent clientSpawnedEvent = new ClientSpawnedEvent();
            clientSpawnedEvent.setSlot(Integer.parseInt(matcher.group(1)));

            Map<String, String> userinfo = parseUserinfo(matcher.group(2));
            for (Map.Entry<String, String> entry : userinfo.entrySet()) {
                switch (entry.getKey()) {
                    case "team":
                        clientSpawnedEvent.setTeam(Team.fromValue(entry.getValue()));
                        break;
                    case "forcepowers":
                        clientSpawnedEvent.setForcePowers(entry.getValue());
                        break;
                    case "ip":
                        String[] split = entry.getValue().split(":");
                        clientSpawnedEvent.setIp(split[0]);
                        clientSpawnedEvent.setPort(Integer.parseInt(split[1]));
                        break;
                    case "rate":
                        clientSpawnedEvent.setRate(Integer.parseInt(entry.getValue()));
                        break;
                    case "snaps":
                        clientSpawnedEvent.setSnaps(Integer.parseInt(entry.getValue()));
                        break;
                    case "name":
                        clientSpawnedEvent.setName(entry.getValue());
                        break;
                    case "model":
                        clientSpawnedEvent.setModel(entry.getValue());
                        break;
                    case "color1":
                        clientSpawnedEvent.setColor1(Integer.parseInt(entry.getValue()));
                        break;
                    case "color2":
                        clientSpawnedEvent.setColor2(Integer.parseInt(entry.getValue()));
                        break;
                    case "color3":
                        clientSpawnedEvent.setColor3(Integer.parseInt(entry.getValue()));
                        break;
                    case "color4":
                        clientSpawnedEvent.setColor4(Integer.parseInt(entry.getValue()));
                        break;
                    case "handicap":
                        clientSpawnedEvent.setHandicap(Integer.parseInt(entry.getValue()));
                        break;
                    case "sex":
                        if (entry.getValue().equals("male")) {
                            clientSpawnedEvent.setSex(Gender.M);
                        } else if (entry.getValue().equals("female")) {
                            clientSpawnedEvent.setSex(Gender.F);
                        }
                        break;
                    case "cg_predictItems":
                        clientSpawnedEvent.setCgPredictItems(Integer.parseInt(entry.getValue()));
                        break;
                    case "saber1":
                        clientSpawnedEvent.setSaber1(entry.getValue());
                        break;
                    case "saber2":
                        clientSpawnedEvent.setSaber2(entry.getValue());
                        break;
                    case "char_color_red":
                        clientSpawnedEvent.setCharColorRed(Integer.parseInt(entry.getValue()));
                        break;
                    case "char_color_green":
                        clientSpawnedEvent.setCharColorGreen(Integer.parseInt(entry.getValue()));
                        break;
                    case "char_color_blue":
                        clientSpawnedEvent.setCharColorBlue(Integer.parseInt(entry.getValue()));
                        break;
                    case "jp":
                        clientSpawnedEvent.setJp(Integer.parseInt(entry.getValue()));
                        break;
                    case "pbindicator":
                        clientSpawnedEvent.setPbIndicator(Integer.parseInt(entry.getValue()) == 1);
                        break;
                    case "teamtask":
                        clientSpawnedEvent.setTeamTask(Integer.parseInt(entry.getValue()));
                        break;
                    case "teamoverlay":
                        clientSpawnedEvent.setTeamOverlay(Integer.parseInt(entry.getValue()) == 1);
                        break;
                }
            }

            return clientSpawnedEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
