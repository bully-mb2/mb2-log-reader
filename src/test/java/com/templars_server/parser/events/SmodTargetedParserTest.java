package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.SmodEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SmodTargetedParserTest {

    private SmodTargetedParser smodTargetedParser;

    @BeforeEach
    void beforeEach() {
        smodTargetedParser = new SmodTargetedParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedValues() {
        String testCommand = "ban";
        String testAdminName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testAdminIp = "127.0.0.1";
        int testAdminPort = 29070;
        int testTargetSlot = 0;
        String testTargetName = "^1Target";
        String testTargetIp = "127.0.0.2";
        int testTargetPort = 29071;
        String testLine = "SMOD command (" + testCommand + ") executed by " + testAdminName + "(adminID: " + testAdminId + ") (IP: " + testAdminIp + ":" + testAdminPort + ") " +
                "against " + testTargetSlot + " (" + testTargetSlot + " resolved to " + testTargetName + " (IP: " + testTargetIp + ":" + testTargetPort + ")";

        SmodEvent actualEvent = smodTargetedParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getAdminName()).isEqualTo(testAdminName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
        assertThat(actualEvent.getAdminIp()).isEqualTo(testAdminIp);
        assertThat(actualEvent.getAdminPort()).isEqualTo(testAdminPort);
        assertThat(actualEvent.getArgs()).isNull();
        assertThat(actualEvent.getTargetSlot()).isEqualTo(testTargetSlot);
        assertThat(actualEvent.getTargetName()).isEqualTo(testTargetName);
        assertThat(actualEvent.getTargetIp()).isEqualTo(testTargetIp);
        assertThat(actualEvent.getTargetPort()).isEqualTo(testTargetPort);
    }

    @Test
    void testParseLine_ValidLineTwoArgs_ExpectedValues() {
        String testCommand = "ban";
        String testAdminName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testAdminIp = "127.0.0.1";
        int testAdminPort = 29070;
        int testTargetSlot = 0;
        String testArg = "r";
        String testTargetName = "^1Target";
        String testTargetIp = "127.0.0.2";
        int testTargetPort = 29071;
        String testLine = "SMOD command (" + testCommand + ") executed by " + testAdminName + "(adminID: " + testAdminId + ") (IP: " + testAdminIp + ":" + testAdminPort + ") " +
                "against " + testTargetSlot + " " + testArg + " (" + testTargetSlot + " resolved to " + testTargetName + " (IP: " + testTargetIp + ":" + testTargetPort + ")";

        SmodEvent actualEvent = smodTargetedParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getAdminName()).isEqualTo(testAdminName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
        assertThat(actualEvent.getAdminIp()).isEqualTo(testAdminIp);
        assertThat(actualEvent.getAdminPort()).isEqualTo(testAdminPort);
        assertThat(actualEvent.getTargetSlot()).isEqualTo(testTargetSlot);
        assertThat(actualEvent.getArgs()).isEqualTo(testArg);
        assertThat(actualEvent.getTargetName()).isEqualTo(testTargetName);
        assertThat(actualEvent.getTargetIp()).isEqualTo(testTargetIp);
        assertThat(actualEvent.getTargetPort()).isEqualTo(testTargetPort);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SmodEvent actualEvent = smodTargetedParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\SMOD command (removeban) executed by ^7|^0+^7| ^0B^7ull^0y[1](adminID: 1) (IP: 127.0.0.1:29070)\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SmodEvent actualEvent = smodTargetedParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
