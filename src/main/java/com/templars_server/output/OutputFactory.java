package com.templars_server.output;

public class OutputFactory {

    public static Output getOutput(String key) throws NoOutputFoundException {
        switch (key) {
            case "mqtt":
                return new MQTTOutput();
        }

        throw new NoOutputFoundException(String.format("No output found for %s", key));
    }

}
