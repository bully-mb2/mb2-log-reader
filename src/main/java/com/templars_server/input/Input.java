package com.templars_server.input;

import java.io.IOException;

public interface Input {

    void open(InputProperties properties) throws IOException;
    String readLine() throws IOException;

}
