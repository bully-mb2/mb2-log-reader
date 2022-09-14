package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ClientConnectEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientConnectParserTest {

    private ClientConnectParser clientConnectParser;

    @BeforeEach
    void beforeEach() {
        clientConnectParser = new ClientConnectParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedSlot() {
        String testName = "Padawan";
        int testSlot = 13;
        String testIp = "355.213.513.198";
        int testPort = 65535;
        String testLine = String.format("ClientConnect: (%s) ID: %d (IP: %s:%d)", testName, testSlot, testIp, testPort);

        ClientConnectEvent actualEvent = clientConnectParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getName()).isEqualTo(testName);
        assertThat(actualEvent.getSlot()).isEqualTo(testSlot);
        assertThat(actualEvent.getIp()).isEqualTo(testIp);
        assertThat(actualEvent.getPort()).isEqualTo(testPort);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        ClientConnectEvent actualEvent = clientConnectParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\ClientConnect: (a) ID: 1 (IP: a:1)\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        ClientConnectEvent actualEvent = clientConnectParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
