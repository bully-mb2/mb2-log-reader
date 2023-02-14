package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.SmodEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SmodSayParserTest {

    private SmodSayParser smodSayParser;

    @BeforeEach
    void beforeEach() {
        smodSayParser = new SmodSayParser();
    }

    @Test
    void testParseLine_ValidSayLine_ExpectedValues() {
        String testCommand = "say";
        String testAdminName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testAdminIp = "127.0.0.1";
        int testAdminPort = 29070;
        String testMessage = "TEST_MESSAGE BLBLBLBL HELLO WORLD";
        String testLine = "SMOD " + testCommand + ": " + testAdminName + " (adminID: " + testAdminId + ") (IP: " + testAdminIp + ":" + testAdminPort + ") : " + testMessage;

        SmodEvent actualEvent = smodSayParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getAdminName()).isEqualTo(testAdminName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
        assertThat(actualEvent.getAdminIp()).isEqualTo(testAdminIp);
        assertThat(actualEvent.getAdminPort()).isEqualTo(testAdminPort);
        assertThat(actualEvent.getArgs()).isEqualTo(testMessage);
    }

    @Test
    void testParseLine_ValidSMSayLine_ExpectedValues() {
        String testCommand = "smsay";
        String testAdminName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testAdminIp = "127.0.0.1";
        int testAdminPort = 29070;
        String testMessage = "TEST_MESSAGE BLBLBLBL HELLO WORLD";
        String testLine = "SMOD " + testCommand + ": " + testAdminName + " (adminID: " + testAdminId + ") (IP: " + testAdminIp + ":" + testAdminPort + ") : " + testMessage;

        SmodEvent actualEvent = smodSayParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getAdminName()).isEqualTo(testAdminName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
        assertThat(actualEvent.getAdminIp()).isEqualTo(testAdminIp);
        assertThat(actualEvent.getAdminPort()).isEqualTo(testAdminPort);
        assertThat(actualEvent.getArgs()).isEqualTo(testMessage);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SmodEvent actualEvent = smodSayParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\SMOD say: ^7|^0+^7| ^0B^7ull^0y (adminID: 1) (IP: 127.0.0.1:29070) : test\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SmodEvent actualEvent = smodSayParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
