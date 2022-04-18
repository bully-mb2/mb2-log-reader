package com.templars_server.input;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class InputProperties {

    private final Properties properties;

    public InputProperties(Properties properties) {
        this.properties = properties;
    }

    private String get(String key) throws MissingInputPropertyException {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new MissingInputPropertyException("No value found for key " + key);
        }

        return value;
    }

    public int getInt(String key) throws MissingInputPropertyException, InvalidInputPropertyException {
        String value = get(key);

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidInputPropertyException(String.format("Couldn't parse (%s=%s) to Int", key, value), e);
        }
    }

    public InetAddress getHost(String key) throws MissingInputPropertyException, InvalidInputPropertyException {
        String value = get(key);

        try {
            return InetAddress.getByName(value);
        } catch (UnknownHostException e) {
            throw new InvalidInputPropertyException(String.format("Couldn't find host for (%s=%s)", key, value), e);
        }
    }

}
