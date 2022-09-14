package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ForcePowers;
import com.templars_server.mb2_log_reader.schema.Gender;
import com.templars_server.mb2_log_reader.schema.MBClass;
import com.templars_server.mb2_log_reader.schema.Team;

import java.util.HashMap;

public class InfoMap {

    private final HashMap<String, String> data;

    public InfoMap() {
        data = new HashMap<>();
    }

    public void put(String key, String value) {
        data.put(key, value);
    }

    public String getString(String key) {
        return data.get(key);
    }

    public int getInt(String key) {
        if (data.containsKey(key)) {
            try {
                return Integer.parseInt(data.get(key));
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        return 0;
    }

    public boolean getBoolean(String key) {
        return getInt(key) == 1;
    }

    public Team getTeam(String key) {
        String value = getString(key);
        try {
            return Team.fromValue(value);
        } catch (IllegalArgumentException ignored) {
        }

        if (value == null) {
            return null;
        }

        switch (value) {
            case "1":
            case "r":
                return Team.REBEL;
            case "2":
            case "b":
                return Team.IMPERIAL;
            case "3":
            case "s":
                return Team.SPECTATOR;
        }

        return null;
    }

    public Gender getGender(String key) {
        String value = getString(key);
        try {
            return Gender.fromValue(value);
        } catch (IllegalArgumentException e) {
            if (value == null) {
                return null;
            }

            switch (value) {
                case "m":
                    return Gender.MALE;
                case "f":
                    return Gender.FEMALE;
            }

        }

        return null;
    }

    public MBClass getMBClass(String key) {
        String value = getString(key);
        try {
            return MBClass.fromValue(value);
        } catch (IllegalArgumentException e) {
            return mbClassFromString(value);
        }
    }

    public ForcePowers getForcePowers(String key) {
        String value = getString(key);
        if (value == null) {
            return null;
        }

        String[] split = value.split("-");
        if (split.length < 3) {
            return null;
        }

        try {
            int mbClassValue = Integer.parseInt(String.format("%s%s", split[0], split[1]));
            ForcePowers forcePowers = new ForcePowers();
            forcePowers.setMbClass(mbClassFromString("" + mbClassValue));
            forcePowers.setPerks(split[2]);
            return forcePowers;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private MBClass mbClassFromString(String value) {
        if (value == null) {
            return null;
        }

        switch (value) {
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

        return null;
    }

}
