package com.templars_server.parser.MBParser;

import com.templars_server.parser.MBParser.events.*;
import com.templars_server.parser.Parser;
import generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MBParser implements Parser {

    private static final Logger LOG = LoggerFactory.getLogger(MBParser.class);

    private final List<MBEventParser<?>> parserList;
    private Marshaller marshaller;

    public MBParser() {
        this.parserList = new ArrayList<>();
        marshaller = null;
    }

    @Override
    public void init(Properties properties) {
        LOG.info("Initializing marshaller");
        try {
            JAXBContext context = JAXBContext.newInstance(
                    ClientBeginEvent.class,
                    ClientConnectEvent.class,
                    ClientDisconnectEvent.class,
                    ClientSpawnedEvent.class,
                    ClientUserinfoChangedEvent.class,
                    InitGameEvent.class,
                    KillEvent.class,
                    SayEvent.class,
                    ShutdownGameEvent.class
            );

            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to initialize marshaller", e);
        }

        LOG.info("Initializing parsers");
        parserList.add(new ClientBeginParser());
        parserList.add(new ClientConnectParser());
        parserList.add(new ClientDisconnectParser());
        parserList.add(new ClientSpawnedParser());
        parserList.add(new ClientUserinfoChangedParser());
        parserList.add(new InitGameParser());
        parserList.add(new KillParser());
        parserList.add(new SayParser());
        parserList.add(new ShutdownGameParser());
        LOG.info("Parsers: " + parserList);
    }

    @Override
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
