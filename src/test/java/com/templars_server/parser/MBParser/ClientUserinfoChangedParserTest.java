package com.templars_server.parser.MBParser;

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
    void testParseLine_ValidLine_ExpectedSlot() {
        int testSlot = 31;
        String testName = "Padawan";
        int testTeam = 2;
        String testModel = "maul_cyber/default";
        int testC1 = 2949375;
        int testC2 = 255;
        String testSC = "none";
        String testS1 = "saber_maul2";
        String testS2 = "none";
        int testSDT = 1;
        int testV = 0;
        int testS = 0;
        int testMBC = 5;

        String testLine = String.format(
                "ClientUserinfoChanged: %d n\\%s\\t\\%d\\m\\%s\\c1\\%d\\c2\\%d\\sc\\%s\\s1\\%s\\s2\\%s\\sdt\\%d\\v\\%d\\s\\%d\\mbc\\%d",
                testSlot,
                testName,
                testTeam,
                testModel,
                testC1,
                testC2,
                testSC,
                testS1,
                testS2,
                testSDT,
                testV,
                testS,
                testMBC
        );

        ClientUserinfoChangedEvent actualEvent = clientUserinfoChangedParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getSlot()).isEqualTo(testSlot);
        assertThat(actualEvent.getName()).isEqualTo(testName);
        assertThat(actualEvent.getTeam()).isEqualTo(Team.B);
        assertThat(actualEvent.getModel()).isEqualTo(testModel);
        assertThat(actualEvent.getColor1()).isEqualTo(testC1);
        assertThat(actualEvent.getColor2()).isEqualTo(testC2);
        assertThat(actualEvent.getSiegeclass()).isEqualTo(testSC);
        assertThat(actualEvent.getSaber1()).isEqualTo(testS1);
        assertThat(actualEvent.getSaber2()).isEqualTo(testS2);
        assertThat(actualEvent.getDesiredTeam()).isEqualTo(Team.R);
        assertThat(actualEvent.getModelVariant()).isEqualTo(testV);
        assertThat(actualEvent.getSaberVariant()).isEqualTo(testS);
        assertThat(actualEvent.getMbClass()).isEqualTo(MBClass.SITH);
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientBegin: 5";

        ClientUserinfoChangedEvent actualEvent = clientUserinfoChangedParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
