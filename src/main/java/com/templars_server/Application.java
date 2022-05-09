package com.templars_server;

import com.templars_server.input.Input;
import com.templars_server.input.InputFactory;
import com.templars_server.input.NoInputFoundException;
import com.templars_server.output.NoOutputFoundException;
import com.templars_server.output.Output;
import com.templars_server.output.OutputFactory;
import com.templars_server.parser.MBParser;
import com.templars_server.util.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Application {

    private static final String CONFIG_FILE = "application.properties";
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException, NoInputFoundException, NoOutputFoundException {
        LOG.info("======== Starting mb2-log-reader ========");
        LOG.info("Loading settings");
        Settings settings = new Settings();
        settings.load(CONFIG_FILE);

        String inputType = settings.get("input");
        LOG.info("Loading input " + inputType);
        Input input = InputFactory.getInput(inputType);
        input.open(settings);

        LOG.info("Loading parser");
        MBParser parser = new MBParser();
        parser.init(settings);

        String outputType = settings.get("output");
        LOG.info("Loading output " + outputType);
        Output output = OutputFactory.getOutput(outputType);
        output.open(settings);

        LOG.info("Starting main loop");
        String line = "";
        while (!Thread.interrupted()) {
            try {
                line = input.readLine();
                LOG.debug(line);
                String xml = parser.parseLine(line);
                if (xml != null) {
                    LOG.debug("\n" + xml);
                    output.writeMessage(xml);
                }
            } catch (Exception e) {
                LOG.error("Uncaught exception while parsing. Last known line: " + line, e);
            }
        }
    }

}
