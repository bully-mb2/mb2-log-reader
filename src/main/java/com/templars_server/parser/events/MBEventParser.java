package com.templars_server.parser.events;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MBEventParser<T> {

    private final Pattern pattern;

    public MBEventParser(String regex) {
        this(Pattern.compile(regex));
    }

    public MBEventParser(Pattern pattern) {
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

    protected InfoMap parseInfoMap(String userinfoLine) {
        InfoMap infoMap = new InfoMap();
        String[] split = userinfoLine.split("\\\\");
        String key = null;

        boolean skipFirst = false;
        if (split[0].isEmpty()) {
            skipFirst = true;
        }

        for (String s : split) {
            if (skipFirst) {
                skipFirst = false;
                continue;
            }

            if (key == null) {
                key = s;
            } else {
                infoMap.put(key, s);
                key = null;
            }
        }

        return infoMap;
    }

}
