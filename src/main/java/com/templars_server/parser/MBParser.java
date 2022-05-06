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

    public MBParser() {
        this.parserList = new ArrayList<>();
        marshaller = null;
    }

    public void init(Settings settings) {
        LOG.info("Initializing marshaller");
        try {
            JAXBContext context = JAXBContext.newInstance("generated");
            marshaller = context.createMarshaller();
            if (settings.getBoolean("parser.verbose")) {
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            }
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
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
        parserList.add(new SendingGameReportParser());
        parserList.add(new ShutdownGameParser());
        LOG.info("Registered " + parserList.size() + " parsers: ");
        for (MBEventParser<?> parser : parserList) {
            LOG.info("   - " + parser.getClass().getSimpleName());
        }
        LOG.info("Ready to parse messages");
    }

    public String parseLine(String line) {
        try {
            StringWriter writer = new StringWriter();
            for (MBEventParser<?> parser : parserList) {
                Object result = parser.parseLine(line);
                if (result != null){
                    marshaller.marshal(result, writer);
                    return writer.toString();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

}
