package com.templars_server.parser.MBParser.events;

import generated.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientSpawnedParserTest {

    private ClientSpawnedParser clientSpawnedParser;

    @BeforeEach
    void beforeEach() {
        clientSpawnedParser = new ClientSpawnedParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedSlot() {
        int testSlot = 31;
        String testTeam = "r";
        String testForcepowers = "1-4-220002100030000330";
        String testIp = "521.521.612.612";
        int testPort = 8629;
        int testRate = 2500;
        int testSnaps = 40;
        String testName = "Padawan";
        String testModel = "clonetrooper_p1/87";
        int testColor1 = 65279;
        int testColor2 = 255;
        int testColor3 = 255;
        int testColor4 = 255;
        int testHandicap = 100;
        String testSex = "male";
        int testCGPredictItems = 1;
        String testSaber1 = "single_1";
        String testSaber2 = "none";
        int testCharColorRed = 0;
        int testCharColorGreen = 0;
        int testCharColorBlue = 0;
        int testJP = 0;
        int testPBIndicator = 1;
        int testTeamTask = 0;
        int testTeamOverlay = 1;

        String testLine = String.format(
                "Player %s spawned with userinfo: " +
                        "\\team\\%s\\forcepowers\\%s\\ip\\%s:%d\\rate\\%d\\snaps\\%d\\name\\%s" +
                        "\\model\\%s\\color1\\%d\\color2\\%d\\handicap\\%d\\sex\\%s\\cg_predictItems\\%d" +
                        "\\saber1\\%s\\saber2\\%s\\char_color_red\\%d\\char_color_green\\%d\\char_color_blue\\%d\\jp\\%d" +
                        "\\pbindicator\\%d\\color3\\%d\\color4\\%d\\teamtask\\%d\\teamoverlay\\%d",
                testSlot,
                testTeam,
                testForcepowers,
                testIp,
                testPort,
                testRate,
                testSnaps,
                testName,
                testModel,
                testColor1,
                testColor2,
                testHandicap,
                testSex,
                testCGPredictItems,
                testSaber1,
                testSaber2,
                testCharColorRed,
                testCharColorGreen,
                testCharColorBlue,
                testJP,
                testPBIndicator,
                testColor3,
                testColor4,
                testTeamTask,
                testTeamOverlay
        );

        ClientSpawnedEvent actualEvent = clientSpawnedParser.parseLine(testLine);

        ForcePowers expectedForcePowers = new ForcePowers();
        expectedForcePowers.setMbClass(MBClass.ARC_TROOPER);
        expectedForcePowers.setPerks("220002100030000330");

        assertThat(actualEvent).isNotNull();
        assertThat(actualEvent.getSlot()).isEqualTo(testSlot);
        assertThat(actualEvent.getTeam()).isEqualTo(Team.R);
        assertThat(actualEvent.getForcePowers()).usingRecursiveComparison().isEqualTo(expectedForcePowers);
        assertThat(actualEvent.getIp()).isEqualTo(testIp);
        assertThat(actualEvent.getPort()).isEqualTo(testPort);
        assertThat(actualEvent.getRate()).isEqualTo(testRate);
        assertThat(actualEvent.getSnaps()).isEqualTo(testSnaps);
        assertThat(actualEvent.getName()).isEqualTo(testName);
        assertThat(actualEvent.getModel()).isEqualTo(testModel);
        assertThat(actualEvent.getColor1()).isEqualTo(testColor1);
        assertThat(actualEvent.getColor2()).isEqualTo(testColor2);
        assertThat(actualEvent.getColor3()).isEqualTo(testColor3);
        assertThat(actualEvent.getColor4()).isEqualTo(testColor4);
        assertThat(actualEvent.getHandicap()).isEqualTo(testHandicap);
        assertThat(actualEvent.getSex()).isEqualTo(Gender.M);
        assertThat(actualEvent.getCgPredictItems()).isEqualTo(testCGPredictItems);
        assertThat(actualEvent.getSaber1()).isEqualTo(testSaber1);
        assertThat(actualEvent.getSaber2()).isEqualTo(testSaber2);
        assertThat(actualEvent.getCharColorRed()).isEqualTo(testCharColorRed);
        assertThat(actualEvent.getCharColorGreen()).isEqualTo(testCharColorGreen);
        assertThat(actualEvent.getCharColorBlue()).isEqualTo(testCharColorBlue);
        assertThat(actualEvent.getJp()).isEqualTo(testJP);
        assertThat(actualEvent.isPbIndicator()).isTrue();
        assertThat(actualEvent.getTeamTask()).isEqualTo(testTeamTask);
        assertThat(actualEvent.isTeamOverlay()).isTrue();
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Padawan\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        ClientSpawnedEvent actualEvent = clientSpawnedParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "ClientUserinfoChanged: 2 n\\Player 1 spawned with userinfo: a\\t\\2\\m\\maul_cyber/default\\c1\\2949375\\c2\\255" +
                "\\sc\\none\\s1\\saber_maul2\\s2\\none\\sdt\\2\\v\\0\\s\\0\\mbc\\5";

        ClientSpawnedEvent actualEvent = clientSpawnedParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
