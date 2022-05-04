package com.templars_server.parser.MBParser;

import generated.Gender;
import generated.MBClass;
import generated.Team;

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

    public int getInt(String key) throws NumberFormatException {
        if (data.containsKey(key)) {
            return Integer.parseInt(data.get(key));
        }

        return 0;
    }

    public boolean getBoolean(String key) throws NumberFormatException {
        return getInt(key) == 1;
    }

    public Team getTeam(String key) {
        String value = getString(key);
        try {
            return Team.fromValue(value);
        } catch (IllegalArgumentException e) {
            switch (value) {
                case "1":
                    return Team.R;
                case "2":
                    return Team.B;
                case "3":
                    return Team.S;
            }

        }

        return null;
    }

    public Gender getGender(String key) {
        String value = getString(key);
        try {
            return Gender.fromValue(value);
        } catch (IllegalArgumentException e) {
            switch (value) {
                case "male":
                    return Gender.M;
                case "female":
                    return Gender.F;
            }

        }

        return null;
    }

    public MBClass getMBClass(String key) {
        String value = getString(key);
        try {
            return MBClass.fromValue(value);
        } catch (IllegalArgumentException e) {
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
        }

        return null;
    }

}
