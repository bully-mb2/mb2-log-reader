package com.templars_server.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ParsableEvent<T> {

    private Pattern pattern;

    public ParsableEvent(String regex) {
        this(Pattern.compile(regex));
    }

    public ParsableEvent(Pattern pattern) {
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

}
