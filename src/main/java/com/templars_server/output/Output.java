package com.templars_server.output;

import com.templars_server.properties.Config;

import java.io.IOException;

public interface Output {

    void open(Config config) throws IOException;
    void writeMessage(String message) throws IOException;

}
