package com.templars_server.input;

import com.templars_server.properties.Config;

import java.io.IOException;

public interface Input {

    void open(Config config) throws IOException;
    String readLine() throws IOException;

}
