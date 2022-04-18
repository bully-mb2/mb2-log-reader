package com.templars_server;

import com.templars_server.input.Input;
import com.templars_server.input.InputFactory;
import com.templars_server.input.InputProperties;
import com.templars_server.input.NoInputFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, NoInputFoundException {
        LOG.info("======== Starting mb2-log-reader ========");
        LOG.info("Loading properties");
        Properties properties = new Properties();
        properties.load(Application.class.getResourceAsStream("/application.properties"));
        LOG.info(properties.toString());

        String inputKey = properties.getProperty("input", "udp");
        LOG.info("Loading input " + inputKey);
        Input input = InputFactory.getInput(inputKey);
        input.open(new InputProperties(properties));
        LOG.info(input.readLine());
    }

}
