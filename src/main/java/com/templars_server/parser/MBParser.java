package com.templars_server.parser;

import com.templars_server.parser.events.*;
import com.templars_server.util.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MBParser {

    private static final Logger LOG = LoggerFactory.getLogger(MBParser.class);

    private final List<MBEventParser<?>> parserList;
    private Marshaller marshaller;
    private String objectSuffix;

    public MBParser() {
        this.parserList = new ArrayList<>();
        marshaller = null;
    }

    public void init(Settings settings) {
        LOG.info("Initializing marshaller");
        try {
            JAXBContext context = JAXBContext.newInstance("com.templars_server.mb2_log_reader.schema");
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, settings.getBoolean("parser.verbose"));
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            objectSuffix = settings.getBoolean("parser.verbose") ? "\n" : "";
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to initialize marshaller", e);
        }

        LOG.info("Initializing parsers");
        parserList.clear();
        parserList.add(new ClientBeginParser());
        parserList.add(new ClientConnectParser());
        parserList.add(new ClientDisconnectParser());
        parserList.add(new ClientSpawnedParser());
        if (!settings.getBoolean("parser.disable.clientuserinfochanged"))
            parserList.add(new ClientUserinfoChangedParser());
        parserList.add(new InitGameParser());
        parserList.add(new KillParser());
        parserList.add(new SayParser());
        parserList.add(new AdminSayParser());
        parserList.add(new SendingGameReportParser());
        parserList.add(new ServerInitializationParser());
        parserList.add(new ShutdownGameParser());
        parserList.add(new FragLimitHitParser());
        parserList.add(new SmodParser());
        parserList.add(new SmodSayParser());
        parserList.add(new SmodSpecialParser());
        parserList.add(new SmodTargetedParser());
        LOG.info("Registered " + parserList.size() + " parsers: ");
        for (MBEventParser<?> parser : parserList) {
            LOG.info("   - " + parser.getClass().getSimpleName());
        }
        LOG.info("Ready to parse messages");
    }

    public List<String> parseLine(String line) {
        List<String> events = new ArrayList<>();
        try {
            for (MBEventParser<?> parser : parserList) {
                Object result = parser.parseLine(line);
                if (result != null){
                    StringWriter writer = new StringWriter();
                    marshaller.marshal(result, writer);
                    events.add(writer + objectSuffix);
                    writer.flush();
                }
            }
        } catch (JAXBException e) {
            LOG.error("Couldn't marshal line, is your schema up to date?", e);
        }

        return events;
    }

}
