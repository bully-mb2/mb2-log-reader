package com.templars_server.parser.MBParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.templars_server.util.TestUtils.loadResourceAsString;
import static org.assertj.core.api.Assertions.assertThat;

class MBParserTest {

    private MBParser mbParser;

    @BeforeEach
    void beforeEach() {
        mbParser = new MBParser();
        mbParser.init(null);
    }

    @Test
    void testParseLine_NormalLogScenario_OutputsExpectedEvents() throws IOException {
        List<Object> actualEvents = new ArrayList<>();
        for (String line : loadResourceAsString(MBParserTest.class, "round_1.log").split("\n")) {
            Object object = mbParser.parseLine(line);
            if (object == null) {
                continue;
            }

            actualEvents.add(object);
        }

        assertThat(actualEvents).hasSize(26);
    }

}
