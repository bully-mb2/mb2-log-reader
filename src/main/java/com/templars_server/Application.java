package com.templars_server;

import com.templars_server.input.Input;
import com.templars_server.input.InputFactory;
import com.templars_server.input.NoInputFoundException;
import com.templars_server.output.NoOutputFoundException;
import com.templars_server.output.Output;
import com.templars_server.output.OutputFactory;
import com.templars_server.parser.MBParser;
import com.templars_server.properties.Config;
import com.templars_server.properties.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Application {

    private static final String CONFIG_FILE = "application.properties";
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, NoInputFoundException, NoOutputFoundException {
        LOG.info("======== Starting mb2-log-reader ========");
        LOG.info("Loading config");
        Properties properties = new Properties();
        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            try (FileInputStream stream = new FileInputStream(file)) {
                properties.load(stream);
            }
        } else {
            try (FileOutputStream stream = new FileOutputStream(file)) {
                LOG.info("No config found, creating from default");
                properties.load(Application.class.getResourceAsStream("/" + CONFIG_FILE));
                properties.store(stream, null);
            }
        }

        Config config = new Config(properties);
        LOG.info(properties.toString());

        String inputType = config.get("input");
        LOG.info("Loading input " + inputType);
        Input input = InputFactory.getInput(inputType);
        input.open(config);

        LOG.info("Loading parser");
        MBParser parser = new MBParser();
        parser.init(config);

        String outputType = config.get("output");
        LOG.info("Loading output " + outputType);
        Output output = OutputFactory.getOutput(outputType);
        output.open(config);

        LOG.info("Starting main loop");
        while (!Thread.interrupted()) {
            String line = input.readLine();
            LOG.debug(line);
            String xml = parser.parseLine(line);
            if (xml != null) {
                LOG.debug("\n" + xml);
                output.writeMessage(xml);
            }
        }
    }

}
