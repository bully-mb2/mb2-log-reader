package com.templars_server.output;

import com.templars_server.util.settings.Settings;

import java.io.IOException;

public interface Output {

    void open(Settings settings) throws IOException;
    void writeMessage(String message) throws IOException;

}
