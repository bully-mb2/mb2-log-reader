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
    private DatagramPacket receivePacket;

    @Override
    public void open(Config config) throws IOException {
        LOG.info("Reading properties");
        int receivePort = config.getInt("input.port");
        externalAddress = config.getHost("input.extern.ip");
        externalPort = config.getInt("input.extern.port");

        LOG.info("Listening on port " + receivePort);
        LOG.info("Dropping any packets not from " + externalAddress.getHostAddress() + ":" + externalPort);
        socket = new DatagramSocket(receivePort);
        byte[] receiveData = new byte[BUFFER_SIZE];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
    }

    @Override
    public String readLine() throws IOException {
        while (true) {
            socket.receive(receivePacket);
            if (!receivePacket.getAddress().equals(externalAddress) || receivePacket.getPort() != externalPort) {
                continue;
            }

            return new String(receivePacket.getData(), "cp1252").replaceAll("\\x00", "").replaceAll("\n", "").strip();
        }
    }

}
