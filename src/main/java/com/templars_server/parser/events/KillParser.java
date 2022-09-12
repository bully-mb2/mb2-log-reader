package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.KillEvent;
import com.templars_server.mb2_log_reader.schema.Weapon;

import java.util.regex.Matcher;

public class KillParser extends MBEventParser<KillEvent> {

    public KillParser() {
        super("^Kill: ([0-9]+) ([0-9]+) ([0-9]+): .*by (.*)$");
    }

    @Override
    protected KillEvent parseEvent(Matcher matcher) {
        try {
            KillEvent killEvent = new KillEvent();
            killEvent.setKiller(Integer.parseInt(matcher.group(1)));
            killEvent.setVictim(Integer.parseInt(matcher.group(2)));

            Weapon weapon = new Weapon();
            weapon.setId(Integer.parseInt(matcher.group(3)));
            weapon.setName(matcher.group(4));
            killEvent.setWeapon(weapon);

            return killEvent;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
