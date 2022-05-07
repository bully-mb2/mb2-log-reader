package com.templars_server.input;

import com.templars_server.util.settings.Settings;

import java.io.IOException;

public interface Input {

    void open(Settings settings) throws IOException;
    String readLine() throws IOException;

}
