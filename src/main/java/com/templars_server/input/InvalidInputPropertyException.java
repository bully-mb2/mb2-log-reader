package com.templars_server.input;

import java.io.IOException;

public class InvalidInputPropertyException extends IOException {

    public InvalidInputPropertyException(String message, Exception e) {
        super(message, e);
    }

}
