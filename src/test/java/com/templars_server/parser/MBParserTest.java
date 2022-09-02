package com.templars_server.parser;

import com.templars_server.util.settings.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static com.templars_server.util.TestUtils.loadResourceAsString;
import static org.assertj.core.api.Assertions.assertThat;

class MBParserTest {

    private MBParser mbParser;

    @BeforeEach
    void beforeEach() {
        mbParser = new MBParser();
    }

    @Test
    void testParseLine_NormalLogScenario_OutputsExpectedEvents() {
        Settings settings = new Settings();
        settings.set("parser.verbose", "false");
        settings.set("parser.disable.clientuserinfochanged", "false");
        mbParser.init(settings);

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

        assertThat(actualEvents).hasSize(32);
        assertThat(expectedLog).isEqualToIgnoringNewLines(actualLog.toString());
    }

    @Test
    void testParseLine_NormalLogScenarioVerbose_OutputsExpectedEvents() {
        Settings settings = new Settings();
        settings.set("parser.verbose", "true");
        settings.set("parser.disable.clientuserinfochanged", "false");
        mbParser.init(settings);

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

        String expectedLog = loadResourceAsString(MBParserTest.class, "round_1_verbose_expected.txt");

        assertThat(actualEvents).hasSize(32);
        assertThat(expectedLog).isEqualToIgnoringNewLines(actualLog.toString());
    }

    @Test
    void testParseLine_ClientUserinfoChangedDisabledVerbose_OutputsExpectedEvents() {
        Settings settings = new Settings();
        settings.set("parser.verbose", "true");
        settings.set("parser.disable.clientuserinfochanged", "true");
        mbParser.init(settings);

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

        assertThat(actualEvents).hasSize(16);
        assertThat(expectedLog).isEqualToIgnoringNewLines(actualLog.toString());
    }

}
