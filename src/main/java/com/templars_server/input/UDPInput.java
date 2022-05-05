package com.templars_server.input;

import com.templars_server.properties.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

public class UDPInput implements Input {

    private static final Logger LOG = LoggerFactory.getLogger(UDPInput.class);
    private static final int BUFFER_SIZE = 1024;

    private InetAddress externalAddress;
    private int externalPort;

    private DatagramSocket socket;

    @Override
    public void open(Config config) throws IOException {
        LOG.info("Reading config");
        int receivePort = config.getInt("input.port");
        externalAddress = config.getHost("input.extern.ip");
        externalPort = config.getInt("input.extern.port");

        LOG.info("Listening on port " + receivePort);
        socket = new DatagramSocket(receivePort);
        LOG.info("Ready to receive messages from " + externalAddress.getHostAddress() + ":" + externalPort);

    }

    @Override
    public String readLine() throws IOException {
        while (true) {
            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            if (!receivePacket.getAddress().equals(externalAddress) || receivePacket.getPort() != externalPort) {
                continue;
            }

            return new String(receivePacket.getData(), "cp1252").replaceAll("\\x00", "").replaceAll("\n", "").strip();
        }
    }

}
