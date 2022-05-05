package com.templars_server.properties;

public class MissingPropertyException extends RuntimeException {

    public MissingPropertyException(String message) {
        super(message);
    }

}
