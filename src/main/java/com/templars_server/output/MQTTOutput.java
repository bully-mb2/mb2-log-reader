package com.templars_server.output;

import com.templars_server.util.settings.Settings;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MQTTOutput implements Output {

    private static final Logger LOG = LoggerFactory.getLogger(MQTTOutput.class);

    private IMqttClient publisher;
    private String topic;

    @Override
    public void open(Settings settings) throws IOException {
        LOG.info("Reading config");
        int port = settings.getInt("output.port");
        topic = settings.get("output.topic");

        LOG.info("Looking for MQTT broker on port " + port);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setMaxInflight(200);
        options.setConnectionTimeout(10);

        try {
            publisher = new MqttClient(
                    "tcp://localhost:" + port,
                    UUID.randomUUID().toString(),
                    new MemoryPersistence()
            );
            publisher.connect(options);
        } catch (MqttException e) {
            throw new IOException("Couldn't connect to MQTT broker", e);
        }

        LOG.info("Ready to send messages on topic " + topic);
    }

    @Override
    public void writeMessage(String message) throws IOException {
        try {
            publisher.publish(topic, makeMessage(message));
        } catch (MqttException e) {
            throw new IOException("Couldn't publish message to MQTT broker", e);
        }
    }

    private MqttMessage makeMessage(String message) {
        return new MqttMessage(message.getBytes(StandardCharsets.UTF_8));
    }

}
