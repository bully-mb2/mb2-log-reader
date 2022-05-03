package com.templars_server.parser.MBParser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MBEvent<T> {

    private Pattern pattern;

    public MBEvent(String regex) {
        this(Pattern.compile(regex));
    }

    public MBEvent(Pattern pattern) {
        this.pattern = pattern;
    }

    public T parseLine(String line) {
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            return parseEvent(matcher);
        }

        return null;
    }

    protected abstract T parseEvent(Matcher matcher);

    protected Map<String, String> parseUserinfo(String userinfoLine) {
        Map<String, String> userinfo = new HashMap<>();
        String[] split = userinfoLine.split("\\\\");
        String key = null;

        for (String s : split) {
            if (s.isEmpty()) {
                continue;
            }

            if (key == null) {
                key = s;
            } else {
                userinfo.put(key, s);
                key = null;
            }
        }

        return userinfo;
    }

}
