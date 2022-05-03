package com.templars_server.parser.MBParser;

import generated.ClientUserinfoChangedEvent;
import generated.Gender;
import generated.MBClass;
import generated.Team;

import java.util.Map;
import java.util.regex.Matcher;

public class ClientUserinfoChangedParser extends MBEvent<ClientUserinfoChangedEvent> {

    public ClientUserinfoChangedParser() {
        super("^ClientUserinfoChanged: ([0-9]{1,2}) (.*)$");
    }

    @Override
    protected ClientUserinfoChangedEvent parseEvent(Matcher matcher) {
        try {
            ClientUserinfoChangedEvent clientUserinfoChangedEvent = new ClientUserinfoChangedEvent();
            clientUserinfoChangedEvent.setSlot(Integer.parseInt(matcher.group(1)));

            Map<String, String> userinfo = parseUserinfo(matcher.group(2));
            for (Map.Entry<String, String> entry : userinfo.entrySet()) {
                switch (entry.getKey()) {
                    case "n":
                        clientUserinfoChangedEvent.setName(entry.getValue());
                        break;
                    case "t":
                        clientUserinfoChangedEvent.setTeam(parseTeam(entry.getValue()));
                        break;
                    case "m":
                        clientUserinfoChangedEvent.setModel(entry.getValue());
                        break;
                    case "c1":
                        clientUserinfoChangedEvent.setColor1(Integer.parseInt(entry.getValue()));
                        break;
                    case "c2":
                        clientUserinfoChangedEvent.setColor2(Integer.parseInt(entry.getValue()));
                        break;
                    case "sc":
                        clientUserinfoChangedEvent.setSiegeclass(entry.getValue());
                        break;
                    case "s1":
                        clientUserinfoChangedEvent.setSaber1(entry.getValue());
                        break;
                    case "s2":
                        clientUserinfoChangedEvent.setSaber2(entry.getValue());
                        break;
                    case "sdt":
                        clientUserinfoChangedEvent.setDesiredTeam(parseTeam(entry.getValue()));
                        break;
                    case "v":
                        clientUserinfoChangedEvent.setModelVariant(Integer.parseInt(entry.getValue()));
                        break;
                    case "s":
                        clientUserinfoChangedEvent.setSaberVariant(Integer.parseInt(entry.getValue()));
                        break;
                    case "mbc":
                        clientUserinfoChangedEvent.setMbClass(parseMBClass(entry.getValue()));
                        break;
                }
            }

            return clientUserinfoChangedEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Team parseTeam(String teamLine) {
        switch (teamLine) {
            case "1":
                return Team.R;
            case "2":
                return Team.B;
            case "3":
                return Team.S;
        }

        return null;
    }

    private MBClass parseMBClass(String mbClassLine) {
        switch (mbClassLine) {
            case "0":
                return MBClass.NONE;
            case "1":
                return MBClass.IMPERIAL_SOLDIER;
            case "2":
                return MBClass.REBEL_SOLDIER;
            case "3":
                return MBClass.COMMANDER;
            case "4":
                return MBClass.ELITE_TROOPER;
            case "5":
                return MBClass.SITH;
            case "6":
                return MBClass.JEDI;
            case "7":
                return MBClass.BOUNTY_HUNTER;
            case "8":
                return MBClass.HERO;
            case "9":
                return MBClass.SUPER_BATTLE_DROID;
            case "10":
                return MBClass.WOOKIE;
            case "11":
                return MBClass.DROIDEKA;
            case "12":
                return MBClass.CLONE_TROOPER;
            case "13":
                return MBClass.MANDALORIAN;
            case "14":
                return MBClass.ARC_TROOPER;
        }

        return MBClass.NONE;
    }

}
