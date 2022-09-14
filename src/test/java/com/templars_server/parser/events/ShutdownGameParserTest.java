package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ShutdownGameEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ShutdownGameParserTest {

    private ShutdownGameParser shutdownGameParser;

    @BeforeEach
    void beforeEach() {
        shutdownGameParser = new ShutdownGameParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedSlot() {
        String testLine = "ShutdownGame:";

        ShutdownGameEvent actualEvent = shutdownGameParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        ShutdownGameEvent actualEvent = shutdownGameParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\ShutdownGame:\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        ShutdownGameEvent actualEvent = shutdownGameParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
