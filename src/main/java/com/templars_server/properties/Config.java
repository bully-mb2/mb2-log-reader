package com.templars_server.properties;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class Config {

    private final Properties properties;

    public Config(Properties properties) {
        this.properties = properties;
    }

    public String get(String key) throws MissingPropertyException {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new MissingPropertyException("No value found for key " + key);
        }

        return value;
    }

    public int getInt(String key) throws MissingPropertyException, InvalidPropertyException {
        String value = get(key);

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidPropertyException(String.format("Couldn't parse (%s=%s) to int", key, value), e);
        }
    }

    public boolean getBoolean(String key) throws MissingPropertyException, InvalidPropertyException {
        String value = get(key);

        if (value.equals("true")) {
            return true;
        } else if (value.equals("false")) {
            return false;
        }

        throw new InvalidPropertyException(String.format("Couldn't parse (%s=%s) to int", key, value));
    }

    public InetAddress getHost(String key) throws MissingPropertyException, InvalidPropertyException {
        String value = get(key);

        try {
            return InetAddress.getByName(value);
        } catch (UnknownHostException e) {
            throw new InvalidPropertyException(String.format("Couldn't find host for (%s=%s)", key, value), e);
        }
    }

}
