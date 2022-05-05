package com.templars_server.properties;

public class InvalidPropertyException extends RuntimeException {

    public InvalidPropertyException(String message) {
        super(message);
    }

    public InvalidPropertyException(String message, Exception e) {
        super(message, e);
    }

}
