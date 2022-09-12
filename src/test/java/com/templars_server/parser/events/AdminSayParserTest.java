package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.AdminSayEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdminSayParserTest {

    private AdminSayParser adminSayParser;

    @BeforeEach
    void beforeEach() {
        adminSayParser = new AdminSayParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedSlot() {
        String testMessage = "!poll Shogun_FA Nightmare_FA Enclave_FA Dxun_FA Doomgiver_FA Dont_Change";
        String testLine = String.format("SMOD say: Padawan (adminID: 1) (IP: 666.666.666.666:29070) : %s", testMessage);

        AdminSayEvent actualEvent = adminSayParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getMessage()).isEqualTo(testMessage);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "0: say: ^7|^0+^7| ^0B^7ull^0y: \"test\"";

        AdminSayEvent actualEvent = adminSayParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\SMOD say: Padawan (adminID: 1) (IP: 666.666.666.666:29070) : !poll Shogun_FA Nightmare_FA Enclave_FA Dxun_FA Doomgiver_FA Dont_Change\n" +
                "^1Admin\u0019: !poll Shogun_FA Nightmare_FA Enclave_FA Dxun_FA Doomgiver_FA Dont_Change\"test\"\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        AdminSayEvent actualEvent = adminSayParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
