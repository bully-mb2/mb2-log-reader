package com.templars_server.output;

public class OutputFactory {

    public static Output getOutput(String key) throws NoOutputFoundException {
        if (key.equals("mqtt")) {
            return new MQTTOutput();
        }

        throw new NoOutputFoundException(String.format("No output found for %s", key));
    }

}
