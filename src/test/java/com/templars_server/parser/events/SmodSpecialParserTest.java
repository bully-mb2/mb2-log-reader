package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.SmodEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SmodSpecialParserTest {

    private SmodSpecialParser smodSpecialParser;

    @BeforeEach
    void beforeEach() {
        smodSpecialParser = new SmodSpecialParser();
    }

    @Test
    void testParseLine_ValidMuteLine_ExpectedValues() {
        String testCommand = "mute";
        String testTargetName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testArgs = "10";
        String testLine = "Server^7\u0019: " + testTargetName + " ^7was ^1muted ^7for ^3" + testArgs + " minute(s) ^7by Admin ^3#" + testAdminId;

        SmodEvent actualEvent = smodSpecialParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getTargetName()).isEqualTo(testTargetName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
        assertThat(actualEvent.getArgs()).isEqualTo(testArgs);
    }

    @Test
    void testParseLine_ValidUnmuteLine_ExpectedValues() {
        String testCommand = "unmute";
        String testTargetName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testLine = "Server^7\u0019: " + testTargetName + " ^7was ^1unmuted ^7by Admin ^3#" + testAdminId;

        SmodEvent actualEvent = smodSpecialParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getTargetName()).isEqualTo(testTargetName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
    }

    @Test
    void testParseLine_ValidSettkLine_ExpectedValues() {
        String testCommand = "settk";
        String testTargetName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testArgs = "499";
        String testLine = "Server^7\u0019: ^7TK points of " + testTargetName + " ^7were set to ^3" + testArgs + " ^7by Admin ^3#" + testAdminId;

        SmodEvent actualEvent = smodSpecialParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getTargetName()).isEqualTo(testTargetName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
        assertThat(actualEvent.getArgs()).isEqualTo(testArgs);
    }

    @Test
    void testParseLine_ValidMarktkLine_ExpectedValues() {
        String testCommand = "marktk";
        String testTargetName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testArgs = "10";
        String testLine = "Server^7\u0019: " + testTargetName + " ^7put on ^1TK probation ^7for ^3" + testArgs + " minute(s) ^7by Admin ^3#" + testAdminId;

        SmodEvent actualEvent = smodSpecialParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getTargetName()).isEqualTo(testTargetName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
        assertThat(actualEvent.getArgs()).isEqualTo(testArgs);
    }

    @Test
    void testParseLine_ValidUnmarktkLine_ExpectedValues() {
        String testCommand = "unmarktk";
        String testTargetName = "^7|^0+^7| ^0B^7ull^0y";
        int testAdminId = 1;
        String testLine = "Server^7: " + testTargetName + " ^7removed from ^1TK probation ^7by Admin ^3#" + testAdminId;

        SmodEvent actualEvent = smodSpecialParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getCommand()).isEqualTo(testCommand);
        assertThat(actualEvent.getTargetName()).isEqualTo(testTargetName);
        assertThat(actualEvent.getAdminId()).isEqualTo(testAdminId);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SmodEvent actualEvent = smodSpecialParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Server^7\u0019: ^7|^0+^7| ^0B^7ull^0y ^7put on ^1TK probation ^7for ^310 minute(s) ^7by Admin ^3#1\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SmodEvent actualEvent = smodSpecialParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
