package com.templars_server.parser.MBParser;

import generated.ClientBeginEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientBeginParserTest {

    private ClientBeginParser clientBeginParser;

    @BeforeEach
    void beforeEach() {
        clientBeginParser = new ClientBeginParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedSlot() {
        int testSlot = 22;
        String testLine = "ClientBegin: " + testSlot;

        ClientBeginEvent actualEvent = clientBeginParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getSlot()).isEqualTo(testSlot);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        ClientBeginEvent actualEvent = clientBeginParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\ClientBegin: 5\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        ClientBeginEvent actualEvent = clientBeginParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
