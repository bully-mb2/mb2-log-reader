package com.templars_server.input;

public class InputFactory {

    public static Input getInput(String key) throws NoInputFoundException {
        if (key.equals("udp")) {
            return new UDPInput();
        }

        throw new NoInputFoundException(String.format("No input found for %s", key));
    }

}
