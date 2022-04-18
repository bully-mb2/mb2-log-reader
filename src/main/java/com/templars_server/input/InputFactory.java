package com.templars_server.input;

public class InputFactory {

    public static Input getInput(String key) throws NoInputFoundException {
        switch (key) {
            case "udp":
                return new UDPInput();
        }

        throw new NoInputFoundException(String.format("No input found for %s", key));
    }

}
