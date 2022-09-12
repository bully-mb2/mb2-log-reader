package com.templars_server.parser.events;

import com.templars_server.mb2_log_reader.schema.ChatChannel;
import com.templars_server.mb2_log_reader.schema.SayEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SayParserTest {

    private SayParser sayParser;

    @BeforeEach
    void beforeEach() {
        sayParser = new SayParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedSlot() {
        int testSlot = 31;
        ChatChannel testChatChannel = ChatChannel.SAY;
        String testName = "Padawan";
        String testMessage = "test";
        String testLine = String.format("%d: %s: %s: \"%s\"", testSlot, testChatChannel.value(), testName, testMessage);

        SayEvent actualEvent = sayParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getSlot()).isEqualTo(testSlot);
        assertThat(actualEvent.getChatChannel()).isEqualTo(testChatChannel);
        assertThat(actualEvent.getName()).isEqualTo(testName);
        assertThat(actualEvent.getMessage()).isEqualTo(testMessage);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SayEvent actualEvent = sayParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\1: sayteam: Padawan: \"test\"\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        SayEvent actualEvent = sayParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
