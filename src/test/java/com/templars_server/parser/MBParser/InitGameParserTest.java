package com.templars_server.parser.MBParser;

import generated.InitGameEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitGameParserTest {

    private InitGameParser initGameParser;

    @BeforeEach
    void beforeEach() {
        initGameParser = new InitGameParser();
    }

    @Test
    void testParseLine_ValidLine_ExpectedSlot() {
        String testVersion = "JAmp: v1.0.1.0 linux-i386 Dec 31 2021";
        int testTimeLimit = 0;
        int testSVPrivateClients = 0;
        int testSVMinRate = 0;
        int testSVMinPing = 0;
        int testSVMaxRate = 50000;
        int testSVMaxClients = 32;
        int testSVMaxPing = 0;
        String testSVHostname = "^7|^0+^7| ^0T^7emplar Open";
        int testSVFPS = 40;
        int testSVFloodProtectSlow = 1;
        int testSVFloodProtect = 1;
        int testSVAutoDemo = 0;
        int testSVAllowDownload = 0;
        int testProtocol = 26;
        String testMapName = "mb2_commtower";
        String testLocation = "Frankfurt, DE";
        String testGameName = "Movie Battles II V1.9.1.1";
        int testGTeamSwap = 0;
        String testGSiegeTeam1 = "LEG_Good";
        String testGSiegeTeam2 = "LEG_Evil";
        int testGSaberLocking = 1;
        int testGPrivateDuel = 1;
        int testGNeedPass = 0;
        int testGMaxHolocronCarry = 3;
        int testGMaxForceRank = 7;
        int testGJediVMerc = 1;
        int testGGravity = 800;
        int testGGameType = 7;
        int testGForceBasedTeams = 0;
        int testGDuelWeaponDisable = 1;
        int testGCompetitive = 0;
        int testGShuffleTimer = 300;
        int testGDuelTimeLimit = 900;
        int testGDuelFriendlyTeam = 0;
        int testGAuthenticity = 0;
        int testGAntiCheat = 1;
        int testFragLimit = 30;
        int testDuelFragLimit = 10;
        int testDMFlags = 0;
        String testDiscord = "CpyfG7btkz";
        int testCaptureLimit = 0;
        int testBotMinPlayers = 0;
        int testBGFighterAltControl = 0;
        int testTimeAdd = 0;
        int testTKSpec = 400;
        int testTKKick = 600;
        String testRTVRTVM = "3807/3.6c";

        String testLine = String.format(
                "InitGame: \\version\\%s\\timelimit\\%d" +
                        "\\sv_privateClients\\%d\\sv_minRate\\%d\\sv_minPing\\%d\\sv_maxrate\\%d\\sv_maxclients\\%d" +
                        "\\sv_maxPing\\%d\\sv_hostname\\%s\\sv_fps\\%d\\sv_floodProtectSlow\\%d" +
                        "\\sv_floodProtect\\%d\\sv_autoDemo\\%d\\sv_allowdownload\\%d\\protocol\\%d\\mapname\\%s" +
                        "\\location\\%s\\gamename\\%s\\g_teamSwap\\%d\\g_siegeTeam2\\%s" +
                        "\\g_siegeTeam1\\%s\\g_saberLocking\\%d\\g_privateDuel\\%d\\g_needpass\\%d\\g_maxHolocronCarry\\%d" +
                        "\\g_maxForceRank\\%d\\g_jediVmerc\\%d\\g_gravity\\%d\\g_gametype\\%d\\g_forceBasedTeams\\%d" +
                        "\\g_duelWeaponDisable\\%d\\g_competitive\\%d\\g_ShuffleTimer\\%d\\g_DuelTimeLimit\\%d" +
                        "\\g_DuelFriendlyTeam\\%d\\g_Authenticity\\%d\\g_AntiCheat\\%d\\fraglimit\\%d\\duel_fraglimit\\%d" +
                        "\\dmflags\\%d\\discord\\%s\\capturelimit\\%d\\bot_minplayers\\%d\\bg_fighterAltControl\\%d" +
                        "\\TimeAdd\\%d\\TK_Spec\\%d\\TK_Kick\\%d\\RTVRTM\\%s",
                testVersion,
                testTimeLimit,
                testSVPrivateClients,
                testSVMinRate,
                testSVMinPing,
                testSVMaxRate,
                testSVMaxClients,
                testSVMaxPing,
                testSVHostname,
                testSVFPS,
                testSVFloodProtectSlow,
                testSVFloodProtect,
                testSVAutoDemo,
                testSVAllowDownload,
                testProtocol,
                testMapName,
                testLocation,
                testGameName,
                testGTeamSwap,
                testGSiegeTeam1,
                testGSiegeTeam2,
                testGSaberLocking,
                testGPrivateDuel,
                testGNeedPass,
                testGMaxHolocronCarry,
                testGMaxForceRank,
                testGJediVMerc,
                testGGravity,
                testGGameType,
                testGForceBasedTeams,
                testGDuelWeaponDisable,
                testGCompetitive,
                testGShuffleTimer,
                testGDuelTimeLimit,
                testGDuelFriendlyTeam,
                testGAuthenticity,
                testGAntiCheat,
                testFragLimit,
                testDuelFragLimit,
                testDMFlags,
                testDiscord,
                testCaptureLimit,
                testBotMinPlayers,
                testBGFighterAltControl,
                testTimeAdd,
                testTKSpec,
                testTKKick,
                testRTVRTVM
        );

        InitGameEvent actualEvent = initGameParser.parseLine(testLine);

        assertThat(actualEvent).isNotNull();
    }

    @Test
    void testParseLine_InvalidLine_IsNull() {
        String testLine = "ClientBegin: 5";

        InitGameEvent actualEvent = initGameParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

    @Test
    void testParseLine_InvalidLinePartiallyContainingRegex_IsNull() {
        String testLine = "Player %s spawned with userinfo: \\team\\r\\forcepowers\\1-4-220002100030000330" +
                "\\ip\\521.521.612.612:5213\\rate\\2500\\snaps\\40\\name\\InitGame: a";

        InitGameEvent actualEvent = initGameParser.parseLine(testLine);

        assertThat(actualEvent).isNull();
    }

}
