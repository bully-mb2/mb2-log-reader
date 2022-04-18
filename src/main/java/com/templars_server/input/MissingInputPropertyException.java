package com.templars_server.input;

import java.io.IOException;

public class MissingInputPropertyException extends IOException {

    public MissingInputPropertyException(String message) {
        super(message);
    }

}
