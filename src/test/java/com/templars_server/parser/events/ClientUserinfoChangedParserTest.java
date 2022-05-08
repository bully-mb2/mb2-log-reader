package com.templars_server.parser.events;

import generated.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientUserinfoChangedParserTest {

    private ClientUserinfoChangedParser clientUserinfoChangedParser;

    @BeforeEach
    void beforeEach() {
        clientUserinfoChangedParser = new ClientUserinfoChangedParser();
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void testParseLine_ValidLine_ExpectedSlot() {
        int testSlot = 31;
        String testName = "Padawan";
        Team testTeam = Team.REBEL;
        String testModel = "maul_cyber/default";
        int testC1 = 2949375;
        int testC2 = 255;
        String testSC = "none";
        String testS1 = "saber_maul2";
        String testS2 = "none";
        Team testSDT = Team.IMPERIAL;
        int testV = 0;
        int testS = 0;
        MBClass testMBC = MBClass.SITH;

        String testLine = String.format(
                "ClientUserinfoChanged: %d n\\%s\\t\\%d\\m\\%s\\c1\\%d\\c2\\%d\\sc\\%s\\s1\\%s\\s2\\%s\\sdt\\%d\\v\\%d\\s\\%d\\mbc\\%d",
                testSlot,
                testName,
                testTeam.ordinal() + 1,
                testModel,
                testC1,
                testC2,
                testSC,
                testS1,
                testS2,
                testSDT.ordinal() + 1,
                testV,
                testS,
                testMBC.ordinal()
        );

        ClientUserinfoChangedEvent actualEvent = clientUserinfoChangedParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getSlot()).isEqualTo(testSlot);
        assertThat(actualEvent.getName()).isEqualTo(testName);
        assertThat(actualEvent.getTeam()).isEqualTo(testTeam);
        assertThat(actualEvent.getModel()).isEqualTo(testModel);
        assertThat(actualEvent.getColor1()).isEqualTo(testC1);
        assertThat(actualEvent.getColor2()).isEqualTo(testC2);
        assertThat(actualEvent.getSiegeclass()).isEqualTo(testSC);
        assertThat(actualEvent.getSaber1()).isEqualTo(testS1);
        assertThat(actualEvent.getSaber2()).isEqualTo(testS2);
        assertThat(actualEvent.getDesiredTeam()).isEqualTo(testSDT);
        assertThat(actualEvent.getModelVariant()).isEqualTo(testV);
        assertThat(actualEvent.getSaberVariant()).isEqualTo(testS);
        assertThat(actualEvent.getMbClass()).isEqualTo(testMBC);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientBegin: 5";

        ClientUserinfoChangedEvent actualEvent = clientUserinfoChangedParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "Player %s spawned with userinfo: \\team\\r\\forcepowers\\1-4-220002100030000330\\ip\\521.521.612.612:5213" +
                "\\rate\\2500\\snaps\\40\\name\\ClientUserinfoChanged: 1 a";

        ClientUserinfoChangedEvent actualEvent = clientUserinfoChangedParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
