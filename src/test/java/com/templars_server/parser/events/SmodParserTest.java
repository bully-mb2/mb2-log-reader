package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.SmodEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SmodParserTest {

    private SmodParser smodParser;

    @BeforeEach
    void beforeEach() {
        smodParser = new SmodParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedValues() {
        String testCommand = "kick";
        String testAdminName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testAdminIp = "127.0.0.1";
        int testAdminPort = 29070;
        String testLine = "SMOD command (" + testCommand + ") executed by " + testAdminName + "(adminID: " + testAdminId + ") (IP: " + testAdminIp + ":" + testAdminPort + ")";

        SmodEvent actualEvent = smodParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getAdminName()).isEqualTo(testAdminName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
        assertThat(actualEvent.getAdminIp()).isEqualTo(testAdminIp);
        assertThat(actualEvent.getAdminPort()).isEqualTo(testAdminPort);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SmodEvent actualEvent = smodParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\SMOD command (removeban) executed by ^7|^0+^7| ^0B^7ull^0y[1](adminID: 1) (IP: 127.0.0.1:29070)\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SmodEvent actualEvent = smodParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
