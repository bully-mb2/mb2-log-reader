package com.templars_server.parser.MBParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.templars_server.util.TestUtils.loadResourceAsString;
import static org.assertj.core.api.Assertions.assertThat;

class MBParserTest {

    private MBParser mbParser;

    @BeforeEach
    void beforeEach() {
        mbParser = new MBParser();
        mbParser.init(new Properties());
    }

    @Test
    void testParseLine_NormalLogScenario_OutputsExpectedEvents() throws IOException {
        StringWriter actualLog = new StringWriter();
        List<Object> actualEvents = new ArrayList<>();
        for (String line : loadResourceAsString(MBParserTest.class, "round_1.log").split("\n")) {
            String object = mbParser.parseLine(line);
            if (object == null) {
                continue;
            }

            actualEvents.add(object);
            actualLog.write(object);
        }

        String expectedLog = loadResourceAsString(MBParserTest.class, "round_1_expected.txt");

        assertThat(actualEvents).hasSize(27);
        assertThat(actualLog.toString()).isEqualToIgnoringNewLines(expectedLog);
    }

    @Test
    void testParseLine_ClientUserinfoChangedDisabled_OutputsExpectedEvents() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("parser.disable.clientuserinfochanged", "true");
        mbParser.init(properties);
        StringWriter actualLog = new StringWriter();
        List<Object> actualEvents = new ArrayList<>();
        for (String line : loadResourceAsString(MBParserTest.class, "round_1.log").split("\n")) {
            String object = mbParser.parseLine(line);
            if (object == null) {
                continue;
            }

            actualEvents.add(object);
            actualLog.write(object);
        }

        String expectedLog = loadResourceAsString(MBParserTest.class, "round_1_disabled_clientuserinfochanged_expected.txt");

        assertThat(actualEvents).hasSize(11);
        assertThat(actualLog.toString()).isEqualToIgnoringNewLines(expectedLog);
    }

}
